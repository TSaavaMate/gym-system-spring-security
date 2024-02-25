package com.example.jwtdemo.services;

import java.util.function.Function;

public interface ProfileMapper<E, D> extends Function<E,D> {
}
