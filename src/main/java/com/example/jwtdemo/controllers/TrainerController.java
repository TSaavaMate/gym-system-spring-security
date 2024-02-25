package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.dto.TrainerDto;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.models.requests.registrationRequest.TrainerRegistrationRequest;
import com.example.jwtdemo.models.requests.trainerFilterRequest.ActiveTrainersRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTrainerRequest;
import com.example.jwtdemo.models.responses.RegistrationResponse;
import com.example.jwtdemo.services.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping("/{username}")
    public ResponseEntity<TrainerDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(trainerService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> create(@RequestBody TrainerRegistrationRequest request){
        return ResponseEntity.ok(trainerService.create(request));
    }

    @GetMapping("/active-trainers")
    public ResponseEntity<List<TrainerProfile>> findActiveTrainers(@RequestBody ActiveTrainersRequest request){
        return ResponseEntity.ok(trainerService.getActiveTrainers(request));
    }
    @PutMapping
    public ResponseEntity<TrainerDto> updateTrainer(@RequestBody UpdateTrainerRequest request) {
        return ResponseEntity.ok(trainerService.update(request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        trainerService.delete(id);
        return ResponseEntity.ok("deleted");
    }



}
