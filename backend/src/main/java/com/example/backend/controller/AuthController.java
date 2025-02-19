package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.auth.AuthResponseDto;
import com.example.backend.dto.auth.LoginUserDto;
import com.example.backend.dto.auth.RegisterUserDto;
import com.example.backend.services.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    AuthService authService;

    public AuthController(AuthService _authService){
        this.authService = _authService;
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterUserDto request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginUserDto request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
    
}
