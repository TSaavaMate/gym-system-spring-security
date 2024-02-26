package com.example.jwtdemo.services.trainee.traineeTraining;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.repositories.TrainingRepository;
import com.example.jwtdemo.services.trainer.mapper.TrainerProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcreteTraineeTrainingService implements TraineeTrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerProfileMapper profileMapper;

    public List<TrainerProfile> getTraineeTrainers(Trainee trainee){
        var trainings  = trainingRepository.findTrainingByTrainee(trainee);

        return trainings.stream()
                .map(Training::getTrainer)
                .map(profileMapper)
                .toList();
    }

}
