package com.invest.exception;

public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(BaseException.generateMessage(message));
    }

    private static String generateMessage(String message) {
        return message;
    }
}
