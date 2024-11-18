package com.sdk.courier.exception;

import com.sdk.courier.model.ErrorResponse;
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

    /**
     * Tüm exception'ları işler.
     *
     * @param ex Fırlatılan exception
     * @return Uygun bir yanıt
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        Optional<ExceptionHandler<?>> handler = registry.findHandler(ex);

        // Eğer bir handler varsa, exception SDKException olarak işlenir
        if (handler.isPresent() && ex instanceof SDKException sdkException) {
            ErrorResponse response = handler.get().handle(sdkException); // 'handle' metodunun doğru kullanımı
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpStatus()));
        }

        // Beklenmeyen hataları loglayalım
        System.err.println("Unhandled exception: " + ex.getMessage());

        // Varsayılan bir hata yanıtı döndür
        ErrorResponse response = ErrorResponse.builder()
                .errorCode("INTERNAL_ERROR")
                .message("An unexpected error occurred")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .exceptionType(ex.getClass().getSimpleName()) // Exception türü yanıt modeline ekleniyor
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
