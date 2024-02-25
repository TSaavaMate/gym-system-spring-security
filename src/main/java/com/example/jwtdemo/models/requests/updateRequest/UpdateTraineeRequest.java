package com.example.jwtdemo.models.requests.updateRequest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTraineeRequest {
    @NotNull
    private String username;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private Date date_of_birth;
    private String address;
    @NotNull
    private Boolean isActive;
}
