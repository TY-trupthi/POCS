package com.tyss.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MangoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoDbApplication.class, args);
	}

}
