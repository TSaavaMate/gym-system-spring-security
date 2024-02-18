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
@Table(name = "TrainingType")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,name = "trainingType_name")
    private String trainingTypeName;


    @OneToMany(mappedBy = "trainingType", cascade = CascadeType.ALL)
    private List<Training> trainings;

}
