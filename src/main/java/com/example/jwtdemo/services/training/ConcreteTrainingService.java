package com.example.jwtdemo.services.training;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.TrainerTrainingRequest;
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
        return trainingRepository.findByTraineeAndCriteria(
                request.getUsername(),
                request.getPeriodFrom(),
                request.getPeriodTo(),
                request.getTrainerFirstName(),
                request.getTrainingType().getTrainingTypeName()
        );
    }

    @Override
    public List<Training> getTrainerTrainings(TrainerTrainingRequest request) {

        return trainingRepository.findByTrainerAndCriteria(
                request.getUsername(),
                request.getPeriodFrom(),
                request.getPeriodTo(),
                request.getTrainerFirstName(),
                request.getTrainingType().getTrainingTypeName()
        );
    }
}
