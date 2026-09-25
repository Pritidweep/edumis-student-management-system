package com.edumis.edumis.exception;

public class MarkNotFoundException extends RuntimeException {

    public MarkNotFoundException(Long id) {
        super("Mark not found with id: " + id);
    }
}