package com.example.jwtdemo.models.requests.patchRequest;

import lombok.Data;

@Data
public class PatchTrainerRequest {
    private String username;
    private Boolean isActive;
}
