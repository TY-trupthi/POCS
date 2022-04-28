package microservice.ratinginfoservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.ratinginfoservice.pojo.Rating;
import microservice.ratinginfoservice.pojo.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 10);
	}
	
	@RequestMapping("users/{userId}")
	public UserRatings getMovies(@PathVariable("userId") String userId) {
		return new UserRatings(Arrays.asList(new Rating("3", 5), new Rating("5", 6)));		
	}

}
