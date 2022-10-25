package com.example.springbootbuilidingblock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final CustomErrorResponse handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), ex.getMessage(), request.getDescription(false));
        return customErrorResponse;
    }
}
