package com.example.jwtdemo.exceptions;

public class BlockedUserException extends RuntimeException {
    public BlockedUserException() {
        super();
    }

    public BlockedUserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
