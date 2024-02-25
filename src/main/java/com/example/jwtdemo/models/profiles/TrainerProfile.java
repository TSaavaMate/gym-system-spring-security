package com.example.jwtdemo.models.profiles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainerProfile {
    private String username;
    private String firstname;
    private String lastname;
    private String specialization;
}
