package com.example.jwtdemo.services.trainer.mapper;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.services.ProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainerProfileMapper implements ProfileMapper<Trainer, TrainerProfile> {

    @Override
    public TrainerProfile apply(Trainer trainer) {
        return TrainerProfile.builder()
                .firstname(trainer.getUser().getFirstName())
                .lastname(trainer.getUser().getLastName())
                .username(trainer.getUser().getUsername())
                .specialization(trainer.getSpecialization())
                .build();
    }
}
