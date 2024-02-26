package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.dto.TraineeDto;
import com.example.jwtdemo.models.requests.patchRequest.PatchTraineeRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTraineeRequest;
import com.example.jwtdemo.services.DaoService;

public interface TraineeService  extends DaoService<Trainee, TraineeDto,Long> {

    void setActiveState(PatchTraineeRequest request);

    TraineeDto update(UpdateTraineeRequest request);

}
