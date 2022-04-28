package com.tyss.actuatorexample.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DataBaseService implements HealthIndicator{

	private final String DATABASE_SERVICE = "DataBaseService";
	
	@Override
	public Health health() {
		if(checkDataBaseHealth()) {
			return Health.up().withDetail(DATABASE_SERVICE, "Service is running").build();
		}else {
			return Health.down().withDetail(DATABASE_SERVICE, "Service is not available").build();
		}
	}
	
	private boolean checkDataBaseHealth() {
		return true;
	}

}
