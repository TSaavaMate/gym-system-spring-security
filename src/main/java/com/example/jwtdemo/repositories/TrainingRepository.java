package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {

    @Query("SELECT t FROM Training t " +
            "WHERE t.trainee.user.username = :username " +
            "AND (:periodFrom IS NULL OR t.date >= :periodFrom) " +
            "AND (:periodTo IS NULL OR t.date <= :periodTo) " +
            "AND (:trainerName IS NULL OR t.trainer.user.firstName = :trainerName) " +
            "AND (:trainingType IS NULL OR t.trainingType.trainingTypeName = :trainingType)")
    List<Training> findByTraineeAndCriteria(
            @Param("trainee") String username,
            @Param("periodFrom") Date periodFrom,
            @Param("periodTo") Date periodTo,
            @Param("trainerName") String trainerName,
            @Param("trainingType") String trainingType
    );
    @Query("SELECT t FROM Training t " +
            "WHERE t.trainer.user.username = :username " +
            "AND (:periodFrom IS NULL OR t.date >= :periodFrom) " +
            "AND (:periodTo IS NULL OR t.date <= :periodTo) " +
            "AND (:traineeName IS NULL OR t.trainee.user.firstName = :traineeName) " +
            "AND (:trainingType IS NULL OR t.trainingType.trainingTypeName = :trainingType)")

    List<Training> findByTrainerAndCriteria(
            @Param("trainer") String username,
            @Param("periodFrom") Date periodFrom,
            @Param("periodTo") Date periodTo,
            @Param("traineeName") String traineeName,
            @Param("trainingType") String trainingType
    );
}
