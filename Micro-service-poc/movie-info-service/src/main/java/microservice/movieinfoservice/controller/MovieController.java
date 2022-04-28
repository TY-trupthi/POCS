package microservice.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import microservice.movieinfoservice.pojo.Movie;
import microservice.movieinfoservice.pojo.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieController {

	/*
	 * @RequestMapping("/{movieId}") public Movie
	 * getMovieInfo(@PathVariable("movieId") String movieId) { return new
	 * Movie(movieId, "Tagaru"); }
	 */

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		// MovieSummary movieSummary =
		// restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey,
		// MovieSummary.class);
		if (movieId == "3") {
			return new Movie(movieId, "Shadows in Paradise",
					"An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.");
		} else {
			return new Movie(movieId, "Four Rooms",
					"It's Ted the Bellhop's first night on the job...and the hotel's very unusual guests are about to place him in some outrageous predicaments. It seems that this evening's room service is serving up one unbelievable happening after another");
		}

	}

}
