package com.example.jwtdemo.services.trainee.mapper;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.dto.TraineeDto;
import com.example.jwtdemo.services.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TraineeDtoMapper implements DtoMapper<Trainee, TraineeDto> {
    @Override
    public TraineeDto apply(Trainee trainee) {
        return TraineeDto.builder()
                .firstname(trainee.getUser().getFirstName())
                .lastname(trainee.getUser().getLastName())
                .address(trainee.getAddress())
                .dateOfBirth(trainee.getDateOfBirth())
                .isActive(trainee.getUser().getIsActive())
                .build();
    }
}
