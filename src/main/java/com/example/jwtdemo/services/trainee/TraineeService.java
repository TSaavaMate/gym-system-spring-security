package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.requests.UpdateTraineeRequest;
import com.example.jwtdemo.services.DaoService;

public interface TraineeService  extends DaoService<Trainee,Long> {

    Trainee setActiveState(Long id,Boolean state);

    Trainee update(UpdateTraineeRequest request);
}
