package com.es.demo.exceptions;

public class IndexCreationException extends BusinessException {

    public IndexCreationException(String index) {
        super("Index Creation Exception For: " + index);
    }
}
