package com.example.jwtdemo.services.trainingtype;

import com.example.jwtdemo.models.dto.TrainingTypeDto;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingTypeDto> findAll();
}
