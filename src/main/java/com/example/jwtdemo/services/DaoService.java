package com.example.jwtdemo.services;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.requests.UpdateTrainerRequest;

import java.util.Collection;
import java.util.Optional;

public interface DaoService <T,K>{
    Optional<T> findByUsername(String username);

    Collection<T> findAll();

    void delete(K id);
}
