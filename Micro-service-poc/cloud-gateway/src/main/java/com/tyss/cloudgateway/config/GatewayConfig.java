package com.tyss.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	 @Bean
	    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route("movie-catalog-service",r -> r.path("/catalog/**")
	                        .uri("http://localhost:8081/"))

	                .route("movie-info-service",r -> r.path("/movies/**")
	                        .uri("http://localhost:8082/"))
	                
	                .route("rating-info-service",r -> r.path("/ratingsdata/**")
	                        .uri("http://localhost:8083/"))
	                
	                .build();
	    }

}
