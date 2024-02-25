package com.example.jwtdemo.services;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTraineeRequest;
import com.example.jwtdemo.repositories.TraineeRepository;
import com.example.jwtdemo.services.trainee.ConcreteTraineeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConcreteTraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @InjectMocks
    private ConcreteTraineeService traineeService;

    @Test
    public void testFindByUsername() {
        String username = "sampleUsername";
        Trainee trainee = new Trainee();
        when(traineeRepository.findTraineeByUserUsername(username)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.findByUsername(username);

        assertTrue(result.isPresent());
        assertSame(trainee, result.get());
    }

    @Test
    public void testDelete() {

        Long traineeId = 1L;


        traineeService.delete(traineeId);


        verify(traineeRepository, times(1)).deleteById(traineeId);
    }

    @Test
    public void testSetActiveState() {

        Long traineeId = 1L;
        Boolean state = true;

        Trainee trainee = new Trainee();
        trainee.setId(traineeId);
        trainee.setUser(
                User.builder().isActive(false).build()
        );

        when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

        Trainee result = traineeService.setActiveState(traineeId, state);

        assertTrue(result.getUser().getIsActive());
    }

    @Test
    public void testUpdate() {
        UpdateTraineeRequest request = UpdateTraineeRequest.builder().build();

        Trainee trainee = new Trainee();

        when(traineeRepository.findById(any())).thenReturn(Optional.of(trainee));

        Trainee result = traineeService.update(request);

        assertNotNull(result);
    }
}

