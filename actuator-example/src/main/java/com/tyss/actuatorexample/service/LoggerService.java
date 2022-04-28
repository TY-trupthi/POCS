package com.tyss.actuatorexample.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements HealthIndicator{
	
private final String LOGGER_SERVICE = "LoggerService";
	
	@Override
	public Health health() {
		if(checkLoggerHealth()) {
			return Health.up().withDetail(LOGGER_SERVICE, "Service is running").build();
		}else {
			return Health.down().withDetail(LOGGER_SERVICE, "Service is not available").build();
		}
	}
	
	private boolean checkLoggerHealth() {
		return false;
	}


}
