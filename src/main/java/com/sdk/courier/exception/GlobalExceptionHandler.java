package com.sdk.courier.exception;

import com.sdk.courier.exception.CourierServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());


    @ExceptionHandler(CourierServiceException.class)
    public ResponseEntity<String> handleCourierServiceException(CourierServiceException e) {
        logException(e);
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void logException(CourierServiceException e) {
        int statusCode = e.getStatusCode();
        if (statusCode == 500) {
            logger.severe("Internal Server Error: " + e.getMessage() + " at URI: " + e.getRequestUri());
        } else if (statusCode == 404) {
            logger.warning("Courier Not Found: " + e.getMessage() + " at URI: " + e.getRequestUri());
        } else if (statusCode == 403) {
            logger.warning("Access Denied: " + e.getMessage() + " at URI: " + e.getRequestUri());
        } else {
            logger.warning("Courier Service Error: " + e.getMessage() + " at URI: " + e.getRequestUri());
        }
    }
}
