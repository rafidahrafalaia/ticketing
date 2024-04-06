package com.restApi.ticketing.response.success;

import java.util.List;

public class SuccessPaginationResponse {
	private String message;
	private Integer page;
	private Integer size;
	private List<Object> data;

	public SuccessPaginationResponse(String message, List<Object> data, Integer page, Integer size) {
		super();
		this.page = page + 1;
		this.size = size;
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public List<Object> getData() {
		return data;
	}
}
