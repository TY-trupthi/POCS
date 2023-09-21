package com.tyss.annotation.util;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<ValidStatus, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<String> asList = Arrays.asList("Active", "In-active");
		return asList.contains(value);
	}

}
