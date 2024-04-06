package com.restApi.ticketing.response.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.LimitExceededException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CostumizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(errorDetail);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(LimitExceededException.class)
	public final ResponseEntity<ErrorResponse> handleLimitExceededException(LimitExceededException ex, WebRequest request) {
		ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(errorDetail);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) throws Exception {
		ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(errorDetail);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(errorDetail);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
