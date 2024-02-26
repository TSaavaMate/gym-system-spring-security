package com.example.jwtdemo.controllers;

import com.example.jwtdemo.models.dto.TraineeDto;
import com.example.jwtdemo.models.requests.registrationRequest.TraineeRegistrationRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTraineeRequest;
import com.example.jwtdemo.models.responses.RegistrationResponse;
import com.example.jwtdemo.services.trainee.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/trainee")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> create(@RequestBody TraineeRegistrationRequest request){
        return ResponseEntity.ok(traineeService.create(request));
    }
    @GetMapping("/{username}")
    public ResponseEntity<TraineeDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(traineeService.findByUsername(username));
    }

    @PutMapping
    public ResponseEntity<TraineeDto> updateTrainee(@RequestBody UpdateTraineeRequest request) {
        return ResponseEntity.ok(traineeService.update(request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        traineeService.delete(id);
        return ResponseEntity.ok().build();
    }



}
