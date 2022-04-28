package com.tyss.centralizedlogging.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class CommonResponse {
	
	private String message;
	
	private boolean error;
	
	private Date date;
	
	private int code;
	
	private Object data;
	
	public CommonResponse() {
		super();
	}

	public CommonResponse(String message, boolean error,Date date , int code, Object data) {
		super();
		this.message = message;
		this.error = error;
		this.date = date;
		this.code = code;
		this.data = data;
	}
	
	

}
