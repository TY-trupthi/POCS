package microservice.moviecatalogservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import microservice.moviecatalogservice.pojo.Movie;


@FeignClient(name = "movie-info-service", path = "/movies")
public interface MovieControllerFeign {
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId);

}
