package com.example.jwtdemo.services.trainer;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.models.requests.trainerFilterRequest.ActiveTrainersRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTrainerRequest;
import com.example.jwtdemo.services.DaoService;

import java.util.List;

public interface TrainerService extends DaoService<Trainer, TrainerDto, Long> {
    Trainer SetActiveState(Long id,Boolean state);

    TrainerDto update(UpdateTrainerRequest request);

    List<TrainerProfile> getActiveTrainers(ActiveTrainersRequest request);
}
