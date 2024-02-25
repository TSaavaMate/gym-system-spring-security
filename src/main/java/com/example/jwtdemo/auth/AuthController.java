package com.example.jwtdemo.auth;

import com.example.jwtdemo.models.requests.authRequest.AuthenticationRequest;
import com.example.jwtdemo.models.requests.authRequest.ChangePasswordRequest;
import com.example.jwtdemo.models.requests.registrationRequest.UserRegistrationRequest;
import com.example.jwtdemo.models.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegistrationRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        service.authenticate(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request
    ){
        service.changePasswordAndAuthenticate(request);
        return ResponseEntity.ok().build();
    }
}
