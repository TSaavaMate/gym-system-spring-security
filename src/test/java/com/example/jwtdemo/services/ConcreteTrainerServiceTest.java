package com.example.jwtdemo.services;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.models.requests.UpdateTrainerRequest;
import com.example.jwtdemo.repositories.TrainerRepository;
import com.example.jwtdemo.services.trainer.ConcreteTrainerService;
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
public class ConcreteTrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private ConcreteTrainerService trainerService;

    @Test
    public void testFindByUsername() {
        String username = "testUsername";
        Trainer trainer = new Trainer();
        when(trainerRepository.findTrainerByUserUsername(username)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.findByUsername(username);

        assertTrue(result.isPresent());
        assertSame(trainer, result.get());
    }

    @Test
    public void testDelete() {
        Long trainerId = 1L;

        trainerService.delete(trainerId);

        verify(trainerRepository, times(1)).deleteById(trainerId);
    }

    @Test
    public void testSetActiveState() {
        Long trainerId = 1L;
        Boolean state = true;
        Trainer trainer = new Trainer();
        trainer.setId(trainerId);
        trainer.setUser(
                User.builder().isActive(false).build()
        );
        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

        Trainer result = trainerService.SetActiveState(trainerId, state);

        assertTrue(result.getUser().getIsActive());
    }

    @Test
    public void testUpdate() {
        UpdateTrainerRequest request = UpdateTrainerRequest.builder().build();
        Trainer trainer = new Trainer();
        when(trainerRepository.findById(any())).thenReturn(Optional.of(trainer));

        Trainer result = trainerService.update(request);

        assertNotNull(result);
    }
}

