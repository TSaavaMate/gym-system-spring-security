package com.example.jwtdemo.exceptions;

public class JwtParsingException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public JwtParsingException() {
    }

    public JwtParsingException(String message) {
        super(message);
    }
}
