package com.example.jwtdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Training_Type")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long trainingType_id;

    @Column(unique = true, nullable = false,name = "training_type_name")
    private String trainingTypeName;


    @OneToMany(mappedBy = "trainingType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Training> trainings;

}
