package com.example.jwtdemo.models.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerDto {
    private String username;
    private String firstname;
    private String lastname;
    private String specialization;
}
