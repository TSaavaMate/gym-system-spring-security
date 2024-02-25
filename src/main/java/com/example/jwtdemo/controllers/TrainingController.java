package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TraineeTrainingRequest;
import com.example.jwtdemo.models.requests.trainingFilterRequest.TrainerTrainingRequest;
import com.example.jwtdemo.services.training.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/training")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;
    @PostMapping("/trainee/trainings")
    public ResponseEntity<List<Training>> getTraineeTrainingsList(@RequestBody TraineeTrainingRequest request){

        var trainings = trainingService.getTraineeTrainings(request);

        return ResponseEntity.ok(trainings);
    }
    @PostMapping("/trainer/trainings")
    public ResponseEntity<List<Training>> getTrainerTrainingsList(@RequestBody TrainerTrainingRequest request){

        var trainings = trainingService.getTrainerTrainings(request);

        return ResponseEntity.ok(trainings);
    }
}
