package com.es.demo.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super("App Exception");
    }

    public BusinessException(String message) {
        super(message);
    }
}
