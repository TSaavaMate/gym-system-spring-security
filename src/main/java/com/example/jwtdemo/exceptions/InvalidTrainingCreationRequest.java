package com.example.jwtdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTrainingCreationRequest extends RuntimeException {
    public InvalidTrainingCreationRequest() {
        super();
    }

    public InvalidTrainingCreationRequest(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
