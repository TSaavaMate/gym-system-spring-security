package com.example.jwtdemo.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TraineeTrainingDto {
    private String name;
    private Date trainingDate;
    private String trainingType;
    private Integer duration;
    private String trainerName;
}
