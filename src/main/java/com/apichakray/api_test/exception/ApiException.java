package com.apichakray.api_test.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiException {

    private final String message;
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
