package microservice.moviecatalogservice.dto;

public class SuccessResponse {

	private Object data;

	private String message;

	public SuccessResponse() {
		super();
	}

	public SuccessResponse(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
