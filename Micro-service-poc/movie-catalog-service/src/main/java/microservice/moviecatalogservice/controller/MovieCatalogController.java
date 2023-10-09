package microservice.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservice.moviecatalogservice.dto.SuccessResponse;
import microservice.moviecatalogservice.pojo.CatalogItem;
import microservice.moviecatalogservice.pojo.Movie;
import microservice.moviecatalogservice.pojo.Rating;
import microservice.moviecatalogservice.pojo.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	RestTemplate restTemplate;

	private static final String MOVIE_CATALOG_SERVICE = "movieCatalogService";

	Logger logger = LoggerFactory.getLogger(MovieCatalogController.class);

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private MovieControllerFeign movieControllerFeign;

	@RequestMapping("/{userId}")
	//@CircuitBreaker(name = MOVIE_CATALOG_SERVICE, fallbackMethod = "movieCatalogFallBack")
	public ResponseEntity<SuccessResponse> getCatalogItem(@PathVariable String userId) {
		logger.info("Inside getCatalogItem");
		// UserRatings userRatings =
		// restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId,
		// UserRatings.class);
		// UserRatings userRatings =
		// restTemplate.getForObject("http://rating-info-service/ratingsdata/users/"+userId,
		// UserRatings.class);
		List<CatalogItem> catalogItems = new ArrayList<>();
		// for (Rating rating : userRatings.getUserRathings()) {
		// Movie movie =
		// restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),
		// Movie.class);
		// Movie movie =
		// restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),
		// Movie.class);
		/*
		 * Movie movie = webClientBuilder.build() //webclient .get() // request method
		 * type .uri("http://localhost:8082/movies/"+rating.getMovieId()).retrieve()
		 * .bodyToMono(Movie.class) .block();//wait
		 */
		Movie movieInfo = movieControllerFeign.getMovieInfo(userId);
		System.err.println(movieInfo);
		catalogItems.add(new CatalogItem(movieInfo.getMovieName(), movieInfo.getDescription(), 1));
		// }

		return new ResponseEntity<>(new SuccessResponse(catalogItems, "Fetched successfully"), HttpStatus.OK);
	}

//	public ResponseEntity<SuccessResponse> movieCatalogFallBack(String userId, Throwable e) {
//		logger.error("Inside fallback");
//		return new ResponseEntity<>(new SuccessResponse(new ArrayList<>(), "Service down"),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
