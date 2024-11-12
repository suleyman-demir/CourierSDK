package com.sdk.courier.exception;


import com.sdk.courier.exception.CourierServiceException;

import java.net.URI;
import java.util.Set;

/**
 * A specific response handler for the Courier Service SDK that throws CourierServiceException
 * when an error response is encountered.
 */
public class CourierResponseHandler extends ResponseHandler {

    /**
     * Constructs a new CourierResponseHandler with a specified set of success status codes.
     *
     * @param successStatusCodes a set of HTTP status codes that are considered successful responses.
     */
    public CourierResponseHandler(Set<Integer> successStatusCodes) {
        super(successStatusCodes);
    }

    /**
     * Handles the error case by throwing a CourierServiceException with details about the failed request.
     *
     * @param statusCode    the HTTP status code from the failed request.
     * @param requestUri    the URI used in the failed request, for tracking the API endpoint.
     * @param responseBody  the response body from the server, which may contain error details.
     */
    @Override
    protected void handleError(int statusCode, URI requestUri, String responseBody) {
        throw new CourierServiceException(
                "Failed to process courier request",
                statusCode,
                requestUri,
                responseBody
        );
    }
}


