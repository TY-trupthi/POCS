package com.example.springradis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRadisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRadisApplication.class, args);
	}

}
