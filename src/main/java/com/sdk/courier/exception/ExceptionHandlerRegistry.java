package com.sdk.courier.exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ExceptionHandlerRegistry {

    private final Map<Class<? extends SDKException>, ExceptionHandler<?>> handlers = new HashMap<>();

    public ExceptionHandlerRegistry() {
        // Burada varsayılan handler'lar kaydedilir
        registerHandler(new ValidationExceptionHandler());
    }

    /**
     * Yeni bir handler ekler.
     *
     * @param handler ExceptionHandler instance
     * @throws IllegalArgumentException Null handler eklenmeye çalışıldığında
     */
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

    /**
     * Mevcut bir handler'ı yenisiyle değiştirir.
     *
     * @param exceptionType Exception türü
     * @param newHandler    Yeni handler
     * @throws IllegalArgumentException Null yeni handler sağlanırsa
     */
    public void replaceHandler(Class<? extends SDKException> exceptionType, ExceptionHandler<?> newHandler) {
        if (newHandler == null) {
            throw new IllegalArgumentException("New handler cannot be null.");
        }
        handlers.put(exceptionType, newHandler);
    }

    /**
     * Belirtilen türdeki exception için handler'ı kaldırır.
     *
     * @param exceptionType Exception türü
     */
    public void removeHandler(Class<? extends SDKException> exceptionType) {
        handlers.remove(exceptionType);
    }

    /**
     * Exception için uygun handler'ı bulur.
     *
     * @param exception Hata nesnesi
     * @return Uygun handler, bulunamazsa boş Optional
     */
    public Optional<ExceptionHandler<?>> findHandler(Throwable exception) {
        if (exception == null) {
            return Optional.empty();
        }

        ExceptionHandler<?> handler = handlers.get(exception.getClass());
        return Optional.ofNullable(handler);
    }



}
