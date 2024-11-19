package com.sdk.courier.exception;

public abstract class BaseSDKException extends RuntimeException implements SDKException {
    private final String errorCode;
    private final int httpStatus;

    protected BaseSDKException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }
}

