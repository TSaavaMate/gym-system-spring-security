package com.example.jwtdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCredentialException extends RuntimeException{

    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException() {
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
