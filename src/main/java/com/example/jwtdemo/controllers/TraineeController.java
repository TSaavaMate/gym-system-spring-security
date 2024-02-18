package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.requests.UpdateTraineeRequest;
import com.example.jwtdemo.services.trainee.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/trainee")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;

    @GetMapping
    public ResponseEntity<Collection<Trainee>> getAll() {
        return ResponseEntity.ok(traineeService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<Trainee>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(traineeService.findByUsername(username));
    }

    @PutMapping
    public ResponseEntity<Trainee> updateTrainee(@RequestBody UpdateTraineeRequest request) {
        return ResponseEntity.ok(traineeService.update(request));
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<Trainee> activate(@PathVariable Long id) {
        return ResponseEntity.ok(traineeService.SetActive(id));
    }
    @GetMapping("/deactivate/{id}")
    public ResponseEntity<Trainee> deActivate(@PathVariable Long id) {
        return ResponseEntity.ok(traineeService.SetNotActive(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        traineeService.delete(id);
        return ResponseEntity.ok("deleted");
    }





}
