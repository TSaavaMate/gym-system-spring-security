package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.models.requests.registrationRequest.TrainerRegistrationRequest;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTrainerRequest;
import com.example.jwtdemo.models.responses.RegistrationResponse;
import com.example.jwtdemo.services.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping("/{username}")
    public ResponseEntity<Optional<Trainer>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(trainerService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> create(@RequestBody TrainerRegistrationRequest request){
        return ResponseEntity.ok(trainerService.create(request));
    }
    @GetMapping("findAll")
    public ResponseEntity<Collection<Trainer>> getAll() {
        return ResponseEntity.ok(trainerService.findAll());
    }


    @PutMapping
    public ResponseEntity<Trainer> updateTrainer(@RequestBody UpdateTrainerRequest request) {
        return ResponseEntity.ok(trainerService.update(request));
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<Trainer> activate(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.SetActiveState(id,true));
    }
    @GetMapping("/deactivate/{id}")
    public ResponseEntity<Trainer> deActivate(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.SetActiveState(id,false));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        trainerService.delete(id);
        return ResponseEntity.ok("deleted");
    }



}
