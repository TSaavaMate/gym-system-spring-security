package com.example.jwtdemo.services.trainer.trainerTraining;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.profiles.TraineeProfile;

import java.util.List;

public interface TrainerTrainingService {
    List<TraineeProfile> getTrainerTrainees(Trainer trainer);
}
