package com.example.jwtdemo.services;


import java.util.function.Function;

public interface DtoMapper<E, D> extends Function<E,D> {
}

