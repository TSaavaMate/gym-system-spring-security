package com.example.jwtdemo.services.trainee.traineeTraining;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.profiles.TrainerProfile;

import java.util.List;

public interface TraineeTrainingService {
    List<TrainerProfile> getTraineeTrainers(Trainee trainee);
}
