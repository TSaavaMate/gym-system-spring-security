package com.example.jwtdemo.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class TraineeDto {
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String address;
    private Boolean isActive;
    private List<TrainerDto> trainers;
}
