package com.sdk.courier.exception;

import com.sdk.courier.model.ErrorResponse;

public abstract class BaseExceptionHandler<T extends SDKException> implements ExceptionHandler<T> {
    private final Class<T> exceptionType;

    protected BaseExceptionHandler(Class<T> exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exceptionType.isInstance(exception);
    }

    @Override
    public ErrorResponse handle(T exception) {
        return ErrorResponse.fromException(exception);
    }
}