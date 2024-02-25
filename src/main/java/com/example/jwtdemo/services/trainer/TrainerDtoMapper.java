package com.example.jwtdemo.services.trainer;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrainerDtoMapper implements Function<Trainer, TrainerDto> {

    @Override
    public TrainerDto apply(Trainer trainer) {
        return TrainerDto.builder()
                .firstname(trainer.getUser().getFirstName())
                .lastname(trainer.getUser().getLastName())
                .username(trainer.getUser().getUsername())
                .specialization(trainer.getSpecialization())
                .build();
    }
}
