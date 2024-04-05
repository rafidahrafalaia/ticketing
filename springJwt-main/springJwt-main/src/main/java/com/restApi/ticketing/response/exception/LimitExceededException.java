package com.restApi.ticketing.response.exception;

public class LimitExceededException extends Throwable {
    public LimitExceededException(String transactionExceedsLimit) {
    }
}
