package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.dto.TraineeDto;
import com.example.jwtdemo.services.trainer.TrainerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TraineeDtoMapper implements Function<Trainee, TraineeDto> {
    private final TrainerDtoMapper trainerMapper;
    @Override
    public TraineeDto apply(Trainee trainee) {
        return TraineeDto.builder()
                .firstname(trainee.getUser().getFirstName())
                .lastname(trainee.getUser().getLastName())
                .dateOfBirth(trainee.getDateOfBirth())
                .isActive(trainee.getUser().getIsActive())
                .trainers(trainee.getTrainings().stream()
                        .map(Training::getTrainer)
                        .map(trainerMapper).toList())
                .build();
    }
}
