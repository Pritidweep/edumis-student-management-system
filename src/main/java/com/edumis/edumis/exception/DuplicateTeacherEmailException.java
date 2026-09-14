package com.edumis.edumis.exception;

public class DuplicateTeacherEmailException extends RuntimeException {

    public DuplicateTeacherEmailException(String message) {
        super(message);
    }
}