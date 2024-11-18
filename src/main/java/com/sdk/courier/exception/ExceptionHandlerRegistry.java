package com.sdk.courier.exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ExceptionHandlerRegistry {
    private final Map<Class<? extends SDKException>, ExceptionHandler<?>> handlers = new HashMap<>();

    public ExceptionHandlerRegistry() {
        // Varsayılan handler'ları kaydet
        registerHandler(new ValidationExceptionHandler());
    }

    public void registerHandler(ExceptionHandler<?> handler) {
        handlers.put(handler.getClass().asSubclass(SDKException.class), handler);
    }

    public void replaceHandler(Class<? extends SDKException> exceptionType, ExceptionHandler<?> newHandler) {
        handlers.put(exceptionType, newHandler);
    }

    public void removeHandler(Class<? extends SDKException> exceptionType) {
        handlers.remove(exceptionType);
    }

    public Optional<ExceptionHandler<?>> findHandler(Throwable exception) {
        return handlers.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(exception))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}