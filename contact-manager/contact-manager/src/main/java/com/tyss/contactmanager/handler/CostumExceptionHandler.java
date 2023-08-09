package com.tyss.contactmanager.handler;

import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.contactmanager.dto.SuccessResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CostumExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body((SuccessResponse.builder().error(true)
				.data(ex.getBindingResult().getFieldErrors().stream().map(error -> {
					String[] split = error.getField().split("\\.");
					String field = split.length == 1 ? error.getField() : split[split.length - 1];
					return field.substring(0, 1).toUpperCase() + field.substring(1) + " : " + error.getDefaultMessage();
				}).collect(Collectors.toList())).build()));
	}

}
