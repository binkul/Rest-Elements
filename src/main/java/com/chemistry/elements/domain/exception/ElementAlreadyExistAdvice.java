package com.chemistry.elements.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ElementAlreadyExistAdvice {
    @ExceptionHandler(ElementAlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public String readerNotFoundHandler(ElementAlreadyExistException ex) {
        return ex.getMessage();
    }
}
