package com.example.jwtdemo.services.trainee.traineeTraining;

import com.example.jwtdemo.entities.Trainee;
import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.entities.Training;
import com.example.jwtdemo.exceptions.InvalidUpdateRequestException;
import com.example.jwtdemo.models.profiles.TrainerProfile;
import com.example.jwtdemo.repositories.TrainerRepository;
import com.example.jwtdemo.repositories.TrainingRepository;
import com.example.jwtdemo.services.trainer.mapper.TrainerProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcreteTraineeTrainingService implements TraineeTrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;
    private final TrainerProfileMapper profileMapper;

    public List<TrainerProfile> getTraineeTrainers(Trainee trainee){
        var trainings  = trainingRepository.findTrainingByTrainee(trainee);

        return trainings.stream()
                .map(Training::getTrainer)
                .map(profileMapper)
                .toList();
    }
    public List<TrainerProfile> updateTraineeTrainers(Trainee trainee,List<String> trainers){

        var trainings = trainingRepository.findTrainingByTrainee(trainee);
        if (trainings.size() != trainers.size()) {
            log.warn("could not update trainee trainers with incorrect amount of trainers");
            throw new InvalidUpdateRequestException();
        }

        var traineeTrainers = new Stack<Trainer>();
        trainers.forEach(trainer->{
            var optionalTrainer = trainerRepository.findTrainerByUserUsername(trainer);
            if (optionalTrainer.isEmpty()) {
                log.warn("could not find trainer with username {}",trainer);
                return;
            }

            traineeTrainers.push(optionalTrainer.get());
        });

        trainings.forEach(training ->
                training.setTrainer(traineeTrainers.pop())
                );

        return getTraineeTrainers(trainee);
    }

}
