package com.example.jwtdemo.services.training;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.TrainerTrainingRequest;

import java.util.List;

public interface TrainingService{
    List<Training> getTraineeTrainings(TraineeTrainingRequest request);
    List<Training>  getTrainerTrainings(TrainerTrainingRequest request);
}
