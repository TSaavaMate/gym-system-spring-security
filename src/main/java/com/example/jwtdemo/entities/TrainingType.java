package com.example.jwtdemo.entities;

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
    @Column(name = "id") // Specify the name of the primary key column
    private Long trainingType_id;

    @Column(unique = true, nullable = false,name = "training_type_name")
    private String trainingTypeName;


    @OneToMany(mappedBy = "trainingType", cascade = CascadeType.ALL)
    private List<Training> trainings;

}
