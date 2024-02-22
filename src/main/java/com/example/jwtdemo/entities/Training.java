package com.example.jwtdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Date date;


    @NonNull
    private Integer duration;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", trainee=" + trainee.getUser().getFirstName() +
                ", trainer=" + trainer +
                ", trainingType=" + trainingType.getTrainingTypeName() +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id")
    @JsonIgnore
    private Trainee trainee;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "trainer_id")
    @JsonManagedReference
    private Trainer trainer;


    @ManyToOne
    @JoinColumn(name = "training_Type")
    private TrainingType trainingType;

}

