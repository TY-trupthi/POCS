package com.tyss.test.app.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class UtilClass {
	
	@Value("${test.value}")
	private String testValue;
	
	public void getData() {
		List<String> asList = Arrays.asList(testValue.split(","));
		System.err.println(asList);
	}

}
