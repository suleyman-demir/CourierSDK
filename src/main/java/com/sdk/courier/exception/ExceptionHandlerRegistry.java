package com.sdk.courier.exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ExceptionHandlerRegistry {

    private final Map<Class<? extends SDKException>, ExceptionHandler<?>> handlers = new HashMap<>();

    public ExceptionHandlerRegistry() {

        registerHandler(new ValidationExceptionHandler());
    }


    public void registerHandler(ExceptionHandler<?> handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null.");
        }
        Class<? extends SDKException> exceptionType = handler.getClass().asSubclass(SDKException.class);
        if (handlers.containsKey(exceptionType)) {
            System.out.println("Warning: A handler for this exception type already exists and will be replaced.");
        }
        handlers.put(exceptionType, handler);
    }


    public void replaceHandler(Class<? extends SDKException> exceptionType, ExceptionHandler<?> newHandler) {
        if (newHandler == null) {
            throw new IllegalArgumentException("New handler cannot be null.");
        }
        handlers.put(exceptionType, newHandler);
    }


    public void removeHandler(Class<? extends SDKException> exceptionType) {
        handlers.remove(exceptionType);
    }


    public Optional<ExceptionHandler<?>> findHandler(Throwable exception) {
        if (exception == null) {
            return Optional.empty();
        }

        ExceptionHandler<?> handler = handlers.get(exception.getClass());
        return Optional.ofNullable(handler);
    }



}
