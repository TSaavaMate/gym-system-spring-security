package com.example.jwtdemo.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingTypeDto {
    private Long id;
    private String name;
}
