package com.example.jwtdemo.models.profiles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TraineeProfile {
    private String username;
    private String lastname;
    private String firstname;
}
