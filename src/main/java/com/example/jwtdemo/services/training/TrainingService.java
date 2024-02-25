package com.example.jwtdemo.services.training;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TrainerTrainingRequest;

import java.util.List;

public interface TrainingService{
    List<Training> getTraineeTrainings(TraineeTrainingRequest request);
    List<Training>  getTrainerTrainings(TrainerTrainingRequest request);
}
