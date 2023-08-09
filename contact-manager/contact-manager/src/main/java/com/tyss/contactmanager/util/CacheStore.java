package com.tyss.contactmanager.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {

	private Cache<String, T> cache;

	public CacheStore() {
		cache = CacheBuilder.newBuilder().concurrencyLevel(Runtime.getRuntime().availableProcessors()).build();
	}

	public T get(String key) {
		try {
			return cache.getIfPresent(key);
		} catch (Exception exception) {
			throw new RuntimeException("Data Not Present");
		}
	}

	public void add(String key, T value) {
		cache.put(key, value);
	}

	public void removeData() {
		cache.invalidate(cache);
	}

}
