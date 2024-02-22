package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Trainer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findTrainerByUserUsername(String username);

    void deleteById(@NonNull Long id);
}
