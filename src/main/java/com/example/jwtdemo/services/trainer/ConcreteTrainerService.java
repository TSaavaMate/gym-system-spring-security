package com.example.jwtdemo.services.trainer;

import com.example.jwtdemo.entities.Trainer;
import com.example.jwtdemo.exceptions.ResourceNotFoundException;
import com.example.jwtdemo.models.requests.UpdateTrainerRequest;
import com.example.jwtdemo.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcreteTrainerService implements TrainerService{
    private final Logger logger = LoggerFactory.getLogger(ConcreteTrainerService.class);
    private final TrainerRepository trainerRepository;
    @Override
    public Optional<Trainer> findByUsername(String username) {
        return trainerRepository.findTrainerByUserUsername(username);
    }

    @Override
    public Collection<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        trainerRepository.deleteById(id);
        logger.info("deleted trainee with ID: {}", id);
    }

    @Override
    public Trainer SetActiveState(Long id, Boolean state) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();

        trainer.getUser().setIsActive(state);

        trainerRepository.save(trainer);

        return trainerRepository.findById(id).orElseThrow();
    }

    @Override
    public Trainer update(UpdateTrainerRequest request) {
        var trainer = trainerRepository.findById(request.getId())
                .orElseThrow(() -> {
                    logger.warn("not found trainer with id : {}" , request.getId());
                    return new ResourceNotFoundException("not found trainer with id :" + request.getId());
                });

        if (request.getSpecialization() != null){
            trainer
                    .setSpecialization(request.getSpecialization());
        }else{

            return trainer;
        }



        trainerRepository.save(trainer);

        logger.info("updated training with ID: {}", request.getId());

        return trainerRepository.findById(request.getId()).orElseThrow();

    }


}
