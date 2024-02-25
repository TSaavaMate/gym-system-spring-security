package com.example.jwtdemo.services;

import com.example.jwtdemo.models.requests.registrationRequest.RegistrationRequest;
import com.example.jwtdemo.models.responses.RegistrationResponse;

import java.util.Collection;
import java.util.Optional;

public interface DaoService <T,K>{
    Optional<T> findByUsername(String username);

    RegistrationResponse create(RegistrationRequest request);

    Collection<T> findAll();

    void delete(K id);
}
