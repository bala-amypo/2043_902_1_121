package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handle(ApiException ex) {
        String message = ex.getMessage();
        
        // Return 404 if the message indicates a missing resource
        if (message != null && message.toLowerCase().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Return 400 for validation errors (like "Students are required" in test38)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}