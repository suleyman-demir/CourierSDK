package com.sdk.courier.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseSDKException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value());
    }
}