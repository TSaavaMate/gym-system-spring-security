package com.example.jwtdemo.services.training;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TrainerTrainingRequest;
import com.example.jwtdemo.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcreteTrainingService implements TrainingService {
    private final TrainingRepository trainingRepository;


    @Override
    public List<Training> getTraineeTrainings(TraineeTrainingRequest request) {
        return null;
    }

    @Override
    public List<Training> getTrainerTrainings(TrainerTrainingRequest request) {
        return null;
    }
}
