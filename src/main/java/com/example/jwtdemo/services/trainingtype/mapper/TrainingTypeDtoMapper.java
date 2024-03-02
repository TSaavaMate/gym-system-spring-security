package com.example.jwtdemo.services.trainingtype.mapper;

import com.example.jwtdemo.entities.TrainingType;
import com.example.jwtdemo.models.dto.TrainingTypeDto;
import com.example.jwtdemo.services.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeDtoMapper implements DtoMapper<TrainingType, TrainingTypeDto> {
    @Override
    public TrainingTypeDto apply(TrainingType trainingType) {
        return TrainingTypeDto.builder()
                .id(trainingType.getTrainingType_id())
                .name(trainingType.getTrainingTypeName())
                .build();
    }
}
