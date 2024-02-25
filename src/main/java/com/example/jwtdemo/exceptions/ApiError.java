package com.example.jwtdemo.exceptions;

import java.time.LocalDateTime;

public record ApiError (
         String path,
         String message,
         int statusCode,
        LocalDateTime time
){ }
