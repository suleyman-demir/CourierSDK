package com.sdk.courier.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CourierErrorHandler {

    private static final Logger logger = Logger.getLogger(CourierErrorHandler.class.getName());


    private static final Map<Integer, Consumer<CourierServiceException>> errorHandlers = new HashMap<>();

    static {
        errorHandlers.put(500, e -> logger.severe("Internal Server Eror: " + e.getMessage() + " at URI: " + e.getRequestUri()));
        errorHandlers.put(404, e -> logger.warning("Couurier Not Found: " + e.getMessage() + " at URI: " + e.getRequestUri()));
        errorHandlers.put(403, e -> logger.warning("Access Denied: " + e.getMessage() + " at URI: " + e.getRequestUri()));
        errorHandlers.put(0, e -> logger.warning("Courier Service Error: " + e.getMessage() + " at URI: " + e.getRequestUri()));
    }


    public static void handleException(CourierServiceException e) {
        errorHandlers.getOrDefault(e.getStatusCode(), errorHandlers.get(0)).accept(e);
    }
}
