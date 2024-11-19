package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // bắt mọi exception do mình định nghĩa
public class GlobalExceptionHandel {

    // lắng nghe exception, hứng exception
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(
                ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build()
        );
    }
}
