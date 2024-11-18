package com.sdk.courier.exception;

public class ValidationExceptionHandler extends BaseExceptionHandler<ValidationException> {
    public ValidationExceptionHandler() {
        super(ValidationException.class);
    }
}
