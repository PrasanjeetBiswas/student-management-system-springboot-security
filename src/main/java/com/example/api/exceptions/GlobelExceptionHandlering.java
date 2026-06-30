package com.example.api.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobelExceptionHandlering {
    
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex){
        ErrorResponse error = new ErrorResponse();
        error.setTimeStamp(LocalDateTime.now());
        error.setStatus(404);
        error.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors()
    .forEach(error->{
        errors.put(error.getField(), error.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
}

@ExceptionHandler(DuplicatePhoneException.class)
public ResponseEntity<ErrorResponse> handleDuplicatePhone(DuplicatePhoneException ex){
    ErrorResponse error = new ErrorResponse();

    error.setTimeStamp(LocalDateTime.now());
    error.setStatus(409);
    error.setMessage(ex.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
}

@ExceptionHandler(DuplicateEmailException.class)
public ResponseEntity<ErrorResponse> handleDuplicateEmails(DuplicateEmailException ex){
    ErrorResponse error = new ErrorResponse();
    error.setTimeStamp(LocalDateTime.now());
    error.setStatus(409);
    error.setMessage(ex.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorResponse> handleIllegalArgException(IllegalArgumentException ex){
    ErrorResponse error = new ErrorResponse();

    error.setTimeStamp(LocalDateTime.now());
    error.setStatus(400);
    error.setMessage(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}
}
