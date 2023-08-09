package com.tyss.contactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyss.contactmanager.entity.ContactDetails;
import com.tyss.contactmanager.util.CacheStore;

@Configuration
public class CacheConfig {

	@Bean
	public CacheStore<ContactDetails> getContactCache() {
		return new CacheStore<>();
	}

}
