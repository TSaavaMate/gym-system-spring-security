package com.example.jwtdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUpdateRequestException extends RuntimeException {
    public InvalidUpdateRequestException() {}

    public InvalidUpdateRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
