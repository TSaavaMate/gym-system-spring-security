package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Training;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Long> {

    Optional<Trainee> findTraineeByUserUsername(String username);

    void deleteById(@NonNull Long id);

}
