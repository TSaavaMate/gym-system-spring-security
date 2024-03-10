package com.example.jwtdemo.utils;

import com.example.jwtdemo.exceptions.JwtParsingException;
import jakarta.servlet.http.HttpServletRequest;

public class JwtExtractor {
    public static String extractJwt(HttpServletRequest request) {
        final String authHeader=request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new JwtParsingException("could not parse jwt bearer from request header");
        }

        return authHeader.substring(7);
    }
}
