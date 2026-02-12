package com.shrivardhan.college.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUser(
            StudentAlreadyExistsException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}