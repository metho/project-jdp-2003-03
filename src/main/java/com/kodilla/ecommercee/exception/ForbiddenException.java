package com.kodilla.ecommercee.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(ExceptionType type, String value) {
        super(String.format(type.getMessage(), value));
    }
}
