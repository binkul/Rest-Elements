package com.chemistry.elements.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String readerNotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ElementAlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public String readerNotFoundHandler(ElementAlreadyExistException ex) {
        return ex.getMessage();
    }

}
