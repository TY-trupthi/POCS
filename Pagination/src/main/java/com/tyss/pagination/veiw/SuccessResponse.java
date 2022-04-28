package com.tyss.pagination.veiw;

import lombok.Data;

@Data
public class SuccessResponse {
	
	private Object data;
	
	private boolean error;

	public SuccessResponse() {
		super();
	}
	
	public SuccessResponse(Object data, boolean error) {
		super();
		this.data = data;
		this.error = error;
	}
	
	

}
