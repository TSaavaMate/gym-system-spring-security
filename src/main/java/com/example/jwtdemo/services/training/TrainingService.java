package com.example.jwtdemo.services.training;

import com.example.jwtdemo.models.dto.TraineeTrainingDto;
import com.example.jwtdemo.models.dto.TrainerTrainingDto;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TrainerTrainingRequest;

import java.util.List;

public interface TrainingService{
    List<TraineeTrainingDto> getTraineeTrainings(TraineeTrainingRequest request);
    List<TrainerTrainingDto>  getTrainerTrainings(TrainerTrainingRequest request);
}
