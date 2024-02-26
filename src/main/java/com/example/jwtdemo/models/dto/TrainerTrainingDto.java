package com.example.jwtdemo.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TrainerTrainingDto {
    private String name;
    private Date trainingDate;
    private String trainingType;
    private Integer duration;
    private String traineeName;
}
