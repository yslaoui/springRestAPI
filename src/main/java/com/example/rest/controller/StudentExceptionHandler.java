package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentCustomError> handleException(StudentNotFoundException exc) {
        StudentCustomError studentCustomError = new StudentCustomError();
        studentCustomError.setStatus(HttpStatus.NOT_FOUND.value());
        studentCustomError.setMessage(exc.getMessage());
        studentCustomError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentCustomError>(studentCustomError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentCustomError> handleException(Exception exc) {
        StudentCustomError studentCustomError = new StudentCustomError();
        studentCustomError.setStatus(HttpStatus.BAD_REQUEST.value());
        studentCustomError.setMessage(exc.getMessage());
        studentCustomError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentCustomError>(studentCustomError, HttpStatus.BAD_REQUEST);
    }

}
