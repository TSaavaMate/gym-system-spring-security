package com.example.jwtdemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainer {
    public Trainer(User user, String specialization) {
        this.user = user;
        this.specialization = specialization;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    private String specialization;

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", user=" + user +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
