package microservice.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class MovieCatalogServiceApplication {
	
	  @Bean	  
	  @LoadBalanced 
	  public RestTemplate getRestTemplate() {
		  return new RestTemplate(); 
	  }
		 
	 

	/**
	 * 
	 * setting timeout (solve problem temporarily)
	 * 
	 * @return RestTemplate
	 *//*
		 * @Bean
		 * 
		 * @LoadBalanced public RestTemplate getRestTemplate() {
		 * HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
		 * = new HttpComponentsClientHttpRequestFactory();
		 * httpComponentsClientHttpRequestFactory.setConnectTimeout(3000); return new
		 * RestTemplate(httpComponentsClientHttpRequestFactory); }
		 */

	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
