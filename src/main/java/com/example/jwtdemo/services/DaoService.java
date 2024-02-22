package com.example.jwtdemo.services;

import java.util.Collection;
import java.util.Optional;

public interface DaoService <T,K>{
    Optional<T> findByUsername(String username);

    Collection<T> findAll();

    void delete(K id);
}
