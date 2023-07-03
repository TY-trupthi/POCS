package com.te.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponseDTO {
	
	private String message;
	private Object data;

}
