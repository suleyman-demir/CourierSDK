package com.sdk.courier.exception;

import com.sdk.courier.model.ErrorResponse;

public class ValidationExceptionHandler extends BaseExceptionHandler<ValidationException> {
    public ValidationExceptionHandler() {
        super(ValidationException.class);
    }

    @Override
    public ErrorResponse handle(ValidationException exception) {

        return ErrorResponse.builder()
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .httpStatus(exception.getHttpStatus())
                .build();
    }
}
