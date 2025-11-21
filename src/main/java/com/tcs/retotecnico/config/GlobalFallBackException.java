package com.tcs.retotecnico.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalFallBackException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAny(Exception ex, HttpServletRequest req) {
        return ResponseEntity.badRequest().body("ERROR");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNoBody(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("ERROR");
    }
}
