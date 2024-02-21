package com.example.jwtdemo.auth;

import com.example.jwtdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CredentialConfigurer {
    private final static String Dot = ".";

    private final UserRepository userRepository;

    public String generateUniqueUsername(String firstName, String lastName) {
        StringBuilder username = new StringBuilder(firstName)
                .append(Dot)
                .append(lastName);

        if (userRepository.existsUserByUsername(username.toString())){
            var n = new Random().nextInt(10);
            return generateUniqueUsername(firstName + n,lastName);
        }

        return username.toString();
    }
}
