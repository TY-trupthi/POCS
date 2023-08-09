package com.tyss.contactmanager.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.tyss.contactmanager.dto.SuccessResponse;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<SuccessResponse> handler(Exception exception){
	  return	ResponseEntity.badRequest().body(SuccessResponse.builder().data(exception.getMessage())
				.message(exception.getMessage()).error(Boolean.TRUE).build());
	}

}
