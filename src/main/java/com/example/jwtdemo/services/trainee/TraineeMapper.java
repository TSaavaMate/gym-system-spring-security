package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.exceptions.ResourceNotFoundException;
import com.example.jwtdemo.models.requests.registrationRequest.TraineeRegistrationRequest;
import com.example.jwtdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TraineeMapper implements Function<TraineeRegistrationRequest, Trainee> {
    private final UserRepository userRepository;
    @Override
    public Trainee apply(TraineeRegistrationRequest request) {
        var user = userRepository.findByFirstNameAndLastName(request.getFirstname(), request.getLastname())
                .orElseThrow(ResourceNotFoundException::new);
        return new Trainee(request.getDateOfBirth(),request.getAddress(),user);
    }
}
