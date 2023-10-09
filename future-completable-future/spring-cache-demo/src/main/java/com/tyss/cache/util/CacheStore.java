package com.tyss.cache.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {

	private Cache<String, T> cache;

	public CacheStore() {
		cache = CacheBuilder.newBuilder().concurrencyLevel(Runtime.getRuntime().availableProcessors()).build();
	}

	public T getData(String key) {
		return cache.getIfPresent(key);
	}

	public void addData(String key, T value) {
		if (key != null && value != null) {
			cache.put(key, value);
		}
	}

	public void removeData(String key) {
		if (key != null)
			cache.invalidate(key);
	}
	
	public void removeAll() {
		cache.invalidateAll();
	}

}
