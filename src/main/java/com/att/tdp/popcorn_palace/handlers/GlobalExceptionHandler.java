package com.att.tdp.popcorn_palace.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatusCode().value());

        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", ZonedDateTime.now());
        errorBody.put("status", httpStatus.value());
        errorBody.put("error", httpStatus.getReasonPhrase());  // fixed here
        errorBody.put("message", ex.getReason());              // your custom message
        errorBody.put("path", request.getRequestURI());        // real path

        return new ResponseEntity<>(errorBody, httpStatus);
    }
}
