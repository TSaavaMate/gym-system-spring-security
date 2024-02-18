package com.example.jwtdemo.models.requests;

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
    private Long id;
    private Date date_of_birth;
    private String address;
}
