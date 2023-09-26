package com.example.springjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class CustomExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleExceptions(AuthException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
