package com.example.backend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.example.backend.utils.ErrorResponseGen;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseGen> AuthExceptionHandler(AuthException exception, WebRequest request) {
        ErrorResponseGen errorResponse = ErrorResponseGen.builder()
                .success(false)
                .message(exception.getMessage())
                .data(request.getDescription(false))
                .build();

        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseGen> ErrorHandler(Exception exception, WebRequest request) {
        ErrorResponseGen errorResponse = ErrorResponseGen.builder()
                .success(false)
                .message(exception.getMessage())
                .data(request.getDescription(true))
                .build();

        return ResponseEntity.status(500).body(errorResponse);
    }
}
