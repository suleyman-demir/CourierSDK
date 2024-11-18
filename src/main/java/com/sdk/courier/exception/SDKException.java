package com.sdk.courier.exception;

public interface SDKException {
    String getErrorCode();
    String getMessage();
    int getHttpStatus();
}