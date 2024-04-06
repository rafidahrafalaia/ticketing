package com.restApi.ticketing.response.exception;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
	private ErrorDetail error;

	public ErrorResponse(ErrorDetail error) {
		this.error = error;
	}

	public ErrorDetail getError() {
		return error;
	}

	public void setError(ErrorDetail error) {
		this.error = error;
	}

	public static class ErrorDetail {
		private int code;
		private String message;

		public ErrorDetail(int code, String message) {
			this.code = code;
			this.message = message;
		}

		@JsonProperty("code")
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		@JsonProperty("message")
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
