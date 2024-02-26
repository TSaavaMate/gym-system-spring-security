package com.example.jwtdemo.services.training;

import com.example.jwtdemo.models.dto.TraineeTrainingDto;
import com.example.jwtdemo.models.dto.TrainerTrainingDto;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TrainerTrainingRequest;
import com.example.jwtdemo.repositories.TrainingRepository;
import com.example.jwtdemo.services.training.mapper.TraineeTrainingDtoMapper;
import com.example.jwtdemo.services.training.mapper.TrainerTrainingDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcreteTrainingService implements TrainingService {
    private final TrainingRepository trainingRepository;

    private final TraineeTrainingDtoMapper traineeTrainingMapper;

    private final TrainerTrainingDtoMapper trainerTrainingDtoMapper;


    @Override
    public List<TraineeTrainingDto> getTraineeTrainings(TraineeTrainingRequest request) {

        var trainings = trainingRepository.findTraineeTraining(
                request.getUsername(),
                request.getPeriodFrom(),
                request.getPeriodTo(),
                request.getTrainerFirstName(),
                request.getTrainingTypeName()
        );

        return trainings.stream()
                .map(traineeTrainingMapper)
                .toList();
    }

    @Override
    public List<TrainerTrainingDto> getTrainerTrainings(TrainerTrainingRequest request) {
        var trainings = trainingRepository.findTrainerTraining(
                request.getUsername(),
                request.getPeriodFrom(),
                request.getPeriodTo(),
                request.getTraineeFirstName());

        return trainings.stream()
                .map(trainerTrainingDtoMapper)
                .toList();

    }
}
