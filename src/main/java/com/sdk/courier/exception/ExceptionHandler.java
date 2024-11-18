package com.sdk.courier.exception;

import com.sdk.courier.model.ErrorResponse;

public interface ExceptionHandler<T extends SDKException> {
    boolean canHandle(Throwable exception);
    ErrorResponse handle(T exception);
}