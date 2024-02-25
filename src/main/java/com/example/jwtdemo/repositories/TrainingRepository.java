package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {

    List<Training> findTrainingByTrainee(Trainee trainee);
    List<Training> findTrainingByTrainer(Trainer trainer);
}
