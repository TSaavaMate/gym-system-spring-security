package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt,Integer> {
    Optional<LoginAttempt> findByUserId(Integer userId);
}
