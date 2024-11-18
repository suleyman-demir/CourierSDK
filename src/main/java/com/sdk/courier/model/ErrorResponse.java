package com.sdk.courier.model;

import com.sdk.courier.exception.SDKException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private int httpStatus;
    private LocalDateTime timestamp;

    public static ErrorResponse fromException(SDKException exception) {
        return ErrorResponse.builder()
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .httpStatus(exception.getHttpStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
