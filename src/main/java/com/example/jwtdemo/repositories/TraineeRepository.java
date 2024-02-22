package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Trainee;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Long> {

    Optional<Trainee> findTraineeByUserUsername(String username);
    void deleteById(@NonNull Long id);

}
