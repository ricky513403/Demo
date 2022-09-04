package com.demo.utils.exception;

public class VerifyCodeNotCorrectException extends RuntimeException{

    public VerifyCodeNotCorrectException(String message) {
        super(message);
    }
}
