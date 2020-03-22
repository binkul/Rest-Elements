package com.chemistry.elements.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MissedArgumentAdvice {
    @ExceptionHandler(MissedArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String readerNotFoundHandler(MissedArgumentException ex) {
        return ex.getMessage();
    }
}
