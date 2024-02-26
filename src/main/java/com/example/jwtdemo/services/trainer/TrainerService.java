package com.example.jwtdemo.services.trainer;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.models.requests.patchRequest.PatchTrainerRequest;
import com.example.jwtdemo.models.requests.trainerFilterRequest.ActiveTrainersRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTrainerRequest;
import com.example.jwtdemo.services.DaoService;

import java.util.List;

public interface TrainerService extends DaoService<Trainer, TrainerDto, Long> {
    TrainerDto update(UpdateTrainerRequest request);

    List<TrainerProfile> getActiveTrainers(ActiveTrainersRequest request);

    void setActiveState(PatchTrainerRequest request);
}
