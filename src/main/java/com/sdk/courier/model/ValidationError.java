package com.sdk.courier.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationError {
    private String field;
    private String message;
    private String code;

    public static ValidationError of(String field, String message, String code) {
        return ValidationError.builder()
                .field(field)
                .message(message)
                .code(code)
                .build();
    }
}
