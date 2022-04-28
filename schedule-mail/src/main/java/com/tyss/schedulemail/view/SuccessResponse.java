package com.tyss.schedulemail.view;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


}
