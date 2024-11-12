package com.sdk.courier.exception;

import lombok.Getter;

/**
 * A custom exception class for handling errors specific to the Courier Service SDK.
 * <p>
 * This exception encapsulates details such as the HTTP status code and a descriptive error message,
 * providing additional context about the failure encountered when interacting with the Courier Service API.
 * </p>
 */
@Getter
public class CourierServiceException extends RuntimeException {

    /**
     * The HTTP status code returned from the Courier Service API, indicating the type of error.
     */
    private final int statusCode;

    /**
     * Constructs a new CourierServiceException with the specified detail message and HTTP status code.
     *
     * @param message the detail message describing the nature of the error.
     * @param statusCode the HTTP status code from the failed request, providing additional context.
     */
    public CourierServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Constructs a new CourierServiceException with the specified detail message, HTTP status code, and cause.
     *
     * @param message the detail message describing the nature of the error.
     * @param statusCode the HTTP status code from the failed request, providing additional context.
     * @param cause the underlying cause of the exception, which may provide further details about the failure.
     */
    public CourierServiceException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
