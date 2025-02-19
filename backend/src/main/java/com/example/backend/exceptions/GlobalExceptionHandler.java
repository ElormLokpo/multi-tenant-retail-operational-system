package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.backend.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDto> AuthExceptionHandler(AuthException exception, WebRequest request) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .success(false)
                .message(exception.getMessage())
                .data(request.getDescription(false))
                .build();

        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception, WebRequest request){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .success(false)
                .message(exception.getMessage())
                .data(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> ErrorHandler(Exception exception, WebRequest request) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .success(false)
                .message(exception.getMessage())
                .data(request.getDescription(true))
                .build();

        return ResponseEntity.status(500).body(errorResponse);
    }
}
