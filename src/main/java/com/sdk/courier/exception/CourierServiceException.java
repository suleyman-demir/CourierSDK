package com.sdk.courier.exception;

import lombok.Getter;

/**
 * CourierServiceException is a custom exception class for handling exceptions specific to
 * the Courier Service SDK. It encapsulates error details such as HTTP status code and error messages.
 */
@Getter
public class CourierServiceException extends RuntimeException {

    /**
     * -- GETTER --
     *  Retrieves the HTTP status code associated with the exception.
     *
     * @return the HTTP status code.
     */
    private final int statusCode;

    /**
     * Constructs a new CourierServiceException with a detailed message and HTTP status code.
     *
     * @param message the detail message.
     * @param statusCode the HTTP status code from the failed request.
     */
    public CourierServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Constructs a new CourierServiceException with a detailed message, HTTP status code, and a cause.
     *
     * @param message the detail message.
     * @param statusCode the HTTP status code from the failed request.
     * @param cause the cause of the exception.
     */
    public CourierServiceException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

}
