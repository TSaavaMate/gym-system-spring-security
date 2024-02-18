package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.entities.TrainingType;
import com.example.jwtdemo.models.requests.TrainingCriteriaRequest;
import com.example.jwtdemo.models.requests.UpdateTraineeRequest;
import com.example.jwtdemo.services.DaoService;

import java.util.Date;
import java.util.List;

public interface TraineeService  extends DaoService<Trainee,Long> {

    Trainee SetActive(Long id);

    Trainee SetNotActive(Long id);

    Trainee update(UpdateTraineeRequest request);

    List<Training> getTraineeTrainings(TrainingCriteriaRequest request);

}
