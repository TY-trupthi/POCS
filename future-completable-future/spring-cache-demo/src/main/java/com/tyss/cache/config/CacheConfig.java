package com.tyss.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyss.cache.dto.EmployeeDTO;
import com.tyss.cache.util.CacheStore;

@Configuration
public class CacheConfig {
	
	@Bean
	public CacheStore<EmployeeDTO> getEmployeeCache() {
		return new CacheStore<>();
	}

}
