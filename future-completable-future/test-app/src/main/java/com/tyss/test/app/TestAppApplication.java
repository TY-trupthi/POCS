package com.tyss.test.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "test");
		SpringApplication.run(TestAppApplication.class, args);
	}

}
