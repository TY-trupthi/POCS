
package com.tyss.centralizedlogging.exception;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tyss.centralizedlogging.dto.CommonResponse;

@RestControllerAdvice
@Order(1)
public class CustomExceptionHandler {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<CommonResponse> sqlExceptionHandling(SQLIntegrityConstraintViolationException exception, WebRequest request) {
		String message = "Oops! opeartion failed";
		System.err.println("Hi");
		return new ResponseEntity<CommonResponse>(
				new CommonResponse(exception.getMessage(), true, new Date(), 500, message),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
