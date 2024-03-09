package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.Trainee;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/trainee")
@Profile("local")
public class LocalTraineeController {

    private final List<Trainee> trainees = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Trainee Trainee){
        trainees.add(Trainee);
        return ResponseEntity.ok("Trainee created successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<Trainee> findByUsername(@PathVariable String username) {
        Optional<Trainee> traineeOptional = trainees.stream()
                .filter(trainee -> trainee.getUser().getUsername().equals(username))
                .findFirst();
        return traineeOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{username}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable String username, @RequestBody Trainee Trainee) {
        Optional<Trainee> existingTraineeOptional = trainees.stream()
                .filter(trainee -> trainee.getUser().getUsername().equals(username))
                .findFirst();
        if (existingTraineeOptional.isPresent()) {
            Trainee existingTrainee = existingTraineeOptional.get();
            existingTrainee.getUser().setFirstName(Trainee.getUser().getFirstName());
            existingTrainee.getUser().setLastName(Trainee.getUser().getLastName());
            return ResponseEntity.ok(existingTrainee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable String username){
        boolean removed = trainees.removeIf(trainee -> trainee.getUser().getUsername().equals(username));
        if (removed) {
            return ResponseEntity.ok("Trainee deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
