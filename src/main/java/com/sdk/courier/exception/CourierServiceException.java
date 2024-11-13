package com.sdk.courier.exception;

import lombok.Getter;

import java.net.URI;


@Getter
public class CourierServiceException extends RuntimeException {


    private final int statusCode;
    private final URI requestUri;
    private final String responseBody;

    public CourierServiceException(String message, int statusCode, URI requestUri, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.requestUri = requestUri;
        this.responseBody = responseBody;
    }


    public CourierServiceException(String message, int statusCode, URI requestUri, String responseBody, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.requestUri = requestUri;
        this.responseBody = responseBody;
    }
}
