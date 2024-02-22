package com.example.jwtdemo.auth;

import com.example.jwtdemo.config.JwtTokenService;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.models.requests.AuthenticationRequest;
import com.example.jwtdemo.models.requests.ChangePasswordRequest;
import com.example.jwtdemo.models.requests.RegisterRequest;
import com.example.jwtdemo.models.responses.AuthenticationResponse;
import com.example.jwtdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final CredentialConfigurer credentialConfigurer;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {

        String username = credentialConfigurer.generateUniqueUsername(request.getFirstName(), request.getLastName());
        String encoded = passwordEncoder.encode(request.getPassword());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(username)
                .password(encoded)
                .role(request.getRole())
                .isActive(request.getIsActive())
                .build();

        userRepository.save(user);

        var jwt = jwtTokenService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwt = jwtTokenService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse changePasswordAndAuthenticate(ChangePasswordRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        var jwt=jwtTokenService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }

}
