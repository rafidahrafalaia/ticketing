package com.restApi.ticketing.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
