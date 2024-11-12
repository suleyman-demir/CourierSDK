package com.sdk.courier.exception;


import java.net.URI;
import java.util.Optional;
import java.util.Set;

/**
 * An abstract handler class that processes HTTP responses and handles errors for the Courier Service SDK.
 * Implementations should define specific error handling logic in the handleError method.
 */
public abstract class ResponseHandler {

    private final Set<Integer> successStatusCodes;

    /**
     * Constructs a new ResponseHandler with a specified set of success status codes.
     *
     * @param successStatusCodes a set of HTTP status codes that are considered successful responses.
     */
    protected ResponseHandler(Set<Integer> successStatusCodes) {
        this.successStatusCodes = successStatusCodes;
    }

    /**
     * Processes the HTTP response by checking the status code. If the status code is not in the
     * successStatusCodes set, it invokes the handleError method.
     *
     * @param statusCode    the HTTP status code to check.
     * @param requestUri    the URI used in the failed request, for tracking the API endpoint.
     * @param responseBody  the response body from the server, which may contain error details.
     */
    public void processResponse(int statusCode, URI requestUri, String responseBody) {
        Optional.of(statusCode)
                .filter(code-> !
                        successStatusCodes.contains(code))
               .ifPresent(code -> handleError(
                       code,
                       requestUri,
                       responseBody));
    }

    /**
     * Handles the error case by throwing an appropriate exception. This method should be implemented
     * by subclasses to define specific error handling behavior.
     *
     * @param statusCode    the HTTP status code from the failed request.
     * @param requestUri    the URI used in the failed request, for tracking the API endpoint.
     * @param responseBody  the response body from the server, which may contain error details.
     */
    protected abstract void handleError(int statusCode, URI requestUri, String responseBody);
}
