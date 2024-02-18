package com.example.jwtdemo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo-controller")
public class SecuredController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hi,from authed");
    }
}
