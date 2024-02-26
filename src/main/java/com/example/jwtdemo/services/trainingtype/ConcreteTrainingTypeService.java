package com.example.jwtdemo.services.trainingtype;

import com.example.jwtdemo.entities.TrainingType;
import com.example.jwtdemo.models.dto.TrainingTypeDto;
import com.example.jwtdemo.repositories.TrainingTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcreteTrainingTypeService implements TrainingTypeService{

    private final TrainingTypeRepository trainingTypeRepository;

    private final TrainingTypeDtoMapper mapper;
    @Override
    public List<TrainingTypeDto> findAll() {
        var types = trainingTypeRepository.findAll();

        return types.stream()
                .map(mapper)
                .toList();
    }
}
