package com.invest.exception;

import org.springframework.util.StringUtils;

public class EnumNotFoundException extends RuntimeException {

    public EnumNotFoundException() {
        super(EnumNotFoundException.generateMessage());
    }

    private static String generateMessage() {
        return "Enum not exist";
    }
}
