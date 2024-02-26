package com.example.jwtdemo.services;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.repositories.TraineeRepository;
import com.example.jwtdemo.services.trainee.ConcreteTraineeService;
import com.example.jwtdemo.services.trainee.mapper.TraineeDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConcreteTraineeServiceTest {

    @Mock
    private TraineeDtoMapper mapper;
    @Mock
    private TraineeRepository traineeRepository;

    @InjectMocks
    private ConcreteTraineeService traineeService;


    @Test
    public void testDelete() {

        Long traineeId = 1L;


        traineeService.delete(traineeId);


        verify(traineeRepository, times(1)).deleteById(traineeId);
    }


}

