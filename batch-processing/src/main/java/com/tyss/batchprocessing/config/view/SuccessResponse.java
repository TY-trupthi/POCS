package com.tyss.batchprocessing.config.view;

import lombok.Data;

@Data
public class SuccessResponse {
	
	private String message;
	
	private boolean error;

	public SuccessResponse(String message, boolean error) {
		super();
		this.message = message;
		this.error = error;
	}

	public SuccessResponse() {
		super();
	}
	
	

}
