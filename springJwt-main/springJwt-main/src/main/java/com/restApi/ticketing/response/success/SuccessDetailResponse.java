package com.restApi.ticketing.response.success;

public class SuccessDetailResponse implements SuccesResponse {
	private String message;
	private Object data; // Variable declared as Object

	public SuccessDetailResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}