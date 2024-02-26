package com.example.jwtdemo.services.training.mapper;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.dto.TrainerTrainingDto;
import com.example.jwtdemo.services.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainerTrainingDtoMapper implements DtoMapper<Training, TrainerTrainingDto> {
    @Override
    public TrainerTrainingDto apply(Training training) {
        return TrainerTrainingDto.builder()
                .name(training.getName())
                .trainingDate(training.getDate())
                .trainingType(training.getTrainingType().getTrainingTypeName())
                .duration(training.getDuration())
                .traineeName(training.getTrainee().getUser().getFirstName())
                .build();
    }
}
