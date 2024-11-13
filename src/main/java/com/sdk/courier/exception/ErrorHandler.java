package com.sdk.courier.exception;

import com.sdk.courier.exception.CourierServiceException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Component
public class ErrorHandler {

    private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());

    private final Map<Integer, Consumer<CourierServiceException>> errorHandlers = new HashMap<>();

    public ErrorHandler() {
        errorHandlers.put(500, this::logSevere);
        errorHandlers.put(404, this::logWarningNotFound);
        errorHandlers.put(403, this::logWarningAccessDenied);
        errorHandlers.put(0, this::logDefaultWarning);
    }

    public void handle(CourierServiceException e) {
        errorHandlers.getOrDefault(e.getStatusCode(), errorHandlers.get(0)).accept(e);
    }

    private void logSevere(CourierServiceException e) {
        logger.severe("Internal Server Error: " + e.getMessage() + " at URI: " + e.getRequestUri());
    }

    private void logWarningNotFound(CourierServiceException e) {
        logger.warning("Courier Not Found: " + e.getMessage() + " at URI: " + e.getRequestUri());
    }

    private void logWarningAccessDenied(CourierServiceException e) {
        logger.warning("Access Denied: " + e.getMessage() + " at URI: " + e.getRequestUri());
    }

    private void logDefaultWarning(CourierServiceException e) {
        logger.warning("Courier Service Error: " + e.getMessage() + " at URI: " + e.getRequestUri());
    }
}
