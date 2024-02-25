package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.dto.TraineeDto;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.models.requests.trainerFilterRequest.ActiveTrainersRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTraineeRequest;
import com.example.jwtdemo.services.DaoService;

public interface TraineeService  extends DaoService<Trainee, TraineeDto,Long> {

    Trainee setActiveState(Long id,Boolean state);

    TraineeDto update(UpdateTraineeRequest request);

}
