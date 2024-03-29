package com.tyss.contactmanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse {
	
	private String message;
	
	private Object data;
	
	private Boolean error;

}
