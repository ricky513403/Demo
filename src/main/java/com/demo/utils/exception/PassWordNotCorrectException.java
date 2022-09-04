package com.demo.utils.exception;

public class PassWordNotCorrectException extends RuntimeException {
    public PassWordNotCorrectException(String message) {
        super(message);
    }
}
