package com.tyss.annotation.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.annotation.dto.SuccessResponse;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.ok(SuccessResponse.builder().error(true)
				.message(ex.getBindingResult().getFieldErrors().stream().map(error -> {
					String[] split = error.getField().split("\\.");
					String field = split.length == 1 ? error.getField() : split[split.length - 1];
					return field.substring(0, 1).toUpperCase() + field.substring(1) + " : " + error.getDefaultMessage();
				}).collect(Collectors.toList()).toString()).build());
	}

}
