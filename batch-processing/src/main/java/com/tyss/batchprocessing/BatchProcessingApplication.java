package com.tyss.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchProcessingApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "test");
		SpringApplication.run(BatchProcessingApplication.class, args);
	}

}
