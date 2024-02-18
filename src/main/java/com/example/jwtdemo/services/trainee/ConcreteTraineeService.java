package com.example.jwtdemo.services.trainee;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.models.requests.TrainingCriteriaRequest;
import com.example.jwtdemo.models.requests.UpdateTraineeRequest;
import com.example.jwtdemo.repositories.TraineeRepository;
import com.example.jwtdemo.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcreteTraineeService implements TraineeService{

    private final TraineeRepository traineeRepository;

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Trainee> findByUsername(String username) {
        System.out.println(username);
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
    public Trainee SetActive(Long id) {

        return setActiveState(id,true);
    }



    @Override
    public Trainee SetNotActive(Long id) {
        return setActiveState(id,false);
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

    @Override
    public List<Training> getTraineeTrainings(TrainingCriteriaRequest request) {


        var byTraineeAndCriteria = trainingRepository.findByTraineeAndCriteria(
                traineeRepository.findTraineeByUserUsername(request.getUsername()).orElseThrow(),
                request.getPeriodFrom(),
                request.getPeriodTo(),
                request.getTrainerFirstName(),
                request.getTrainingType().getTrainingTypeName()
        );
        return byTraineeAndCriteria;

    }

    private Trainee setActiveState(Long id,boolean isActive) {
        Trainee trainee = traineeRepository.findById(id).orElseThrow();

        trainee.getUser().setIsActive(isActive);

        traineeRepository.save(trainee);

        return traineeRepository.findById(id).orElseThrow();
    }
}
