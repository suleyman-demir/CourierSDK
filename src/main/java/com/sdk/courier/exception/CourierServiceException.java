package com.sdk.courier.exception;

import lombok.Getter;

import java.net.URI;

/**
 * A custom exception class for handling errors specific to the Courier Service SDK.
 * This exception encapsulates details such as the HTTP status code, request URI, and
 * response body, providing additional context about the failure encountered when
 * interacting with the Courier Service API.
 */
@Getter
public class CourierServiceException extends RuntimeException {

    /**
     * The HTTP status code returned from the Courier Service API, indicating the type of error.
     */
    private final int statusCode;

    /**
     * The URI that was used in the failed request, helping trace which endpoint caused the error.
     */
    private final URI requestUri;

    /**
     * The response body received from the server, which might contain additional error details.
     */
    private final String responseBody;

    /**
     * Constructs a new CourierServiceException with the specified detail message, HTTP status code,
     * request URI, and response body.
     *
     * @param message       the detail message describing the nature of the error.
     * @param statusCode    the HTTP status code from the failed request, providing additional context.
     * @param requestUri    the URI used in the failed request, for tracking the API endpoint.
     * @param responseBody  the response body from the server, which may contain error details.
     */
    public CourierServiceException(String message, int statusCode, URI requestUri, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.requestUri = requestUri;
        this.responseBody = responseBody;
    }

    /**
     * Constructs a new CourierServiceException with the specified detail message, HTTP status code,
     * request URI, response body, and cause.
     *
     * @param message       the detail message describing the nature of the error.
     * @param statusCode    the HTTP status code from the failed request, providing additional context.
     * @param requestUri    the URI used in the failed request, for tracking the API endpoint.
     * @param responseBody  the response body from the server, which may contain error details.
     * @param cause         the underlying cause of the exception, which may provide further details about the failure.
     */
    public CourierServiceException(String message, int statusCode, URI requestUri, String responseBody, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.requestUri = requestUri;
        this.responseBody = responseBody;
    }
}
