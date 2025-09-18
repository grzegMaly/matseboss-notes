package com.interview.notes.exceptions;

import com.interview.notes.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            String fieldName = err.getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintException(ConstraintViolationException ex) {

        Map<String, String> result = new HashMap<>();
        ex.getConstraintViolations().forEach(el -> {
            String[] pathParts = el.getPropertyPath().toString().split("\\.");
            String field = pathParts[pathParts.length - 1];
            String message = el.getMessage();
            result.put(field, message);
        });
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handeResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
