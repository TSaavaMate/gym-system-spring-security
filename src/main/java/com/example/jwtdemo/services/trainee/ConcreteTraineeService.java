package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.models.requests.UpdateTraineeRequest;
import com.example.jwtdemo.repositories.TraineeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcreteTraineeService implements TraineeService{

    private final TraineeRepository traineeRepository;

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
    }
    @Override
    public Trainee setActiveState(Long id,Boolean state) {
        Trainee trainee = traineeRepository.findById(id).orElseThrow();

        trainee.getUser().setIsActive(state);

        traineeRepository.save(trainee);

        return traineeRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    @Override
    public Trainee update(UpdateTraineeRequest request)  {

        var trainee = traineeRepository.findById(request.getId()).orElseThrow(BadRequestException::new);

        if (request.getDate_of_birth() != null){
            trainee
                    .setDateOfBirth(request.getDate_of_birth());

        }

        if (request.getAddress() != null){
            trainee
                    .setAddress(request.getAddress());
        }



        traineeRepository.save(trainee);

        return traineeRepository.findById(request.getId()).orElseThrow();


    }
}
