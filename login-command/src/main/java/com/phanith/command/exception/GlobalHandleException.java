package com.phanith.command.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                request.getDescription(false)

        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateException(DuplicateException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                request.getDescription(false)

        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
