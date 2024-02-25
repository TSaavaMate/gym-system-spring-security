package com.example.jwtdemo.services.trainer.mapper;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import com.example.jwtdemo.services.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainerDtoMapper implements DtoMapper<Trainer, TrainerDto> {

    @Override
    public TrainerDto apply(Trainer trainer) {
        return TrainerDto.builder()
                .firstname(trainer.getUser().getFirstName())
                .lastname(trainer.getUser().getLastName())
                .specialization(trainer.getSpecialization())
                .isActive(trainer.getUser().getIsActive())
                .build();
    }
}
