package com.chemistry.elements.domain.exception;

public class MissedArgumentException extends RuntimeException {
    public MissedArgumentException(String message) {
        super(message);
    }
}
