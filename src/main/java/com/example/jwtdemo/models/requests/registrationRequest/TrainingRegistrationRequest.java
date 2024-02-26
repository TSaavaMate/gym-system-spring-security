package com.example.jwtdemo.models.requests.registrationRequest;

import lombok.Data;

import java.util.Date;

@Data
public class TrainingRegistrationRequest {
    private String traineeUsername;
    private String trainerUsername;
    private String name;
    private Date trainingDate;
    private Integer duration;
}
