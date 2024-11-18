package com.sdk.courier.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationError {
    private String fieldName;
    private String errorMessage;
    private String errorCode;

    public static ValidationError of(String fieldName, String errorMessage, String errorCode) {
        return ValidationError.builder()
                .fieldName(fieldName)
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .build();
    }
}

