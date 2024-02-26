package com.example.jwtdemo.services.training.mapper;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.dto.TraineeTrainingDto;
import com.example.jwtdemo.services.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TraineeTrainingDtoMapper implements DtoMapper<Training, TraineeTrainingDto> {
    @Override
    public TraineeTrainingDto apply(Training training) {
        return TraineeTrainingDto.builder()
                .name(training.getName())
                .trainingDate(training.getDate())
                .trainingType(training.getTrainingType().getTrainingTypeName())
                .duration(training.getDuration())
                .trainerName(training.getTrainer().getUser().getFirstName())
                .build();

    }
}
