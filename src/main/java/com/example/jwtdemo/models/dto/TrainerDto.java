package com.example.jwtdemo.models.dto;

import com.example.jwtdemo.models.profiles.TraineeProfile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TrainerDto {
    private String firstname;
    private String lastname;
    private String specialization;
    private Boolean isActive;
    private List<TraineeProfile> trainees;
}
