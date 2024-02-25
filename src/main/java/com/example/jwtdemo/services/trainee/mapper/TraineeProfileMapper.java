package com.example.jwtdemo.services.trainee.mapper;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.profiles.TraineeProfile;
import com.example.jwtdemo.services.ProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class TraineeProfileMapper implements ProfileMapper<Trainee, TraineeProfile> {

    @Override
    public TraineeProfile apply(Trainee trainee) {
        return TraineeProfile.builder()
                .firstname(trainee.getUser().getFirstName())
                .lastname(trainee.getUser().getLastName())
                .username(trainee.getUser().getUsername())
                .build();
    }
}
