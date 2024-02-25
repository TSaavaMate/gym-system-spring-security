package com.example.jwtdemo.services.trainer;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTrainerRequest;
import com.example.jwtdemo.services.DaoService;

public interface TrainerService extends DaoService<Trainer, TrainerDto, Long> {
    Trainer SetActiveState(Long id,Boolean state);

    Trainer update(UpdateTrainerRequest request);

}
