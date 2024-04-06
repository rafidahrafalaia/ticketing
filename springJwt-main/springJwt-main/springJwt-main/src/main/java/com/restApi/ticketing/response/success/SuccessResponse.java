package com.restApi.ticketing.response.success;

public class SuccessResponse {
	private String message;
	private Object data;

	public SuccessResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
