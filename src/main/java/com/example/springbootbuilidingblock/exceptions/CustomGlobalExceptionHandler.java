package com.example.springbootbuilidingblock.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    //MethodArgumentNotValid
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    //HttpRequestMethodNotSupported
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

}
