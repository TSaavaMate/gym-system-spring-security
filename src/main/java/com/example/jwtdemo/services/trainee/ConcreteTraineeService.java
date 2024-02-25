package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.exceptions.ResourceNotFoundException;
import com.example.jwtdemo.models.requests.updateRequest.UpdateTraineeRequest;
import com.example.jwtdemo.models.requests.registrationRequest.RegistrationRequest;
import com.example.jwtdemo.models.requests.registrationRequest.TraineeRegistrationRequest;
import com.example.jwtdemo.models.responses.RegistrationResponse;
import com.example.jwtdemo.repositories.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcreteTraineeService implements TraineeService{
    private final Logger logger = LoggerFactory.getLogger(ConcreteTraineeService.class);

    private final TraineeRepository traineeRepository;

    private final TraineeMapper mapper;

    @Override
    public Optional<Trainee> findByUsername(String username) {
        return traineeRepository.findTraineeByUserUsername(username);
    }



    @Override
    public Collection<Trainee> findAll() {
        return traineeRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        traineeRepository.deleteById(id);
        logger.info("deleted trainee with ID: {}", id);
    }
    @Override
    public Trainee setActiveState(Long id,Boolean state) {
        Trainee trainee = traineeRepository.findById(id).orElseThrow();

        logger.info("found trainee with {}", id);
        trainee.getUser().setIsActive(state);

        traineeRepository.save(trainee);
        logger.info("updated trainee status with {}" , state);
        return traineeRepository.findById(id).orElseThrow();
    }

    @Override
    public Trainee update(UpdateTraineeRequest request)  {

        var trainee = traineeRepository.findById(request.getId())
                .orElseThrow(() -> {
                    logger.warn("not found trainee with id : {}" , request.getId());
                    return new ResourceNotFoundException("not found trainee with id :" + request.getId());
                });

        if (request.getDate_of_birth() != null){
            trainee
                    .setDateOfBirth(request.getDate_of_birth());

        }

        if (request.getAddress() != null){
            trainee
                    .setAddress(request.getAddress());
        }

        traineeRepository.save(trainee);

        logger.info("updated trainee with ID: {}", request.getId());

        return traineeRepository.findById(request.getId()).orElseThrow();


    }
    @Override
    public RegistrationResponse create(RegistrationRequest request) {

        var trainee = mapper.apply((TraineeRegistrationRequest) request);


        traineeRepository.save(trainee);
        return RegistrationResponse.builder()
                .username(trainee.getUser().getUsername())
                .password(trainee.getUser().getPassword())
                .build();
    }
}
