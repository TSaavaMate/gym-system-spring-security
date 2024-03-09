package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt,Integer> {
    Optional<LoginAttempt> findByUserId(Integer userId);
}
