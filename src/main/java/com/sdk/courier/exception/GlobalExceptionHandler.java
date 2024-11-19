package com.sdk.courier.exception;

import com.sdk.courier.model.ErrorResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionHandlerRegistry registry;

    public GlobalExceptionHandler(ExceptionHandlerRegistry registry) {
        this.registry = registry;
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        Optional<ExceptionHandler<?>> handler = registry.findHandler(ex);


        if (handler.isPresent() && ex instanceof SDKException sdkException) {
            ErrorResponse response = handler.get().handle(sdkException);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpStatus()));
        }


        System.err.println("Unhandled exception: " + ex.getMessage());


        ErrorResponse response = ErrorResponse.builder()
                .errorCode("INTERNAL_ERROR")
                .message("An unexpected error occurred")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .exceptionType(ex.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
