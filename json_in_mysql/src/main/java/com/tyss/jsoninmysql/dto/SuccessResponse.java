package com.tyss.jsoninmysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
	
	private Object data;
	
	private boolean error;
	
	private String message;
	

}
