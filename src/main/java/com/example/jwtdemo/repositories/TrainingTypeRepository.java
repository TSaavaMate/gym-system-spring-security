package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType,Long> {
}
