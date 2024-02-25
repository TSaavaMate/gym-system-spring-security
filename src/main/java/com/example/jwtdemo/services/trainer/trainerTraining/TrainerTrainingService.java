package com.example.jwtdemo.services.trainer.trainerTraining;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.profiles.TraineeProfile;
import com.example.jwtdemo.repositories.TrainingRepository;
import com.example.jwtdemo.services.trainee.mapper.TraineeProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerTrainingService {
    private final TrainingRepository trainingRepository;
    private final TraineeProfileMapper traineeProfileMapper;

    public List<TraineeProfile> getTrainerTrainees(Trainer trainer){
        var trainings  = trainingRepository.findTrainingByTrainer(trainer);

        return trainings.stream()
                .map(Training::getTrainee)
                .map(traineeProfileMapper)
                .toList();
    }

}
