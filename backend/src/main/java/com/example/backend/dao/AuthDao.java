package com.example.backend.dao;

import com.example.backend.dto.auth.AuthResponseDto;
import com.example.backend.dto.auth.LoginUserDto;
import com.example.backend.dto.auth.RegisterUserDto;

public interface AuthDao {
    public AuthResponseDto registerUser(RegisterUserDto request);

    public AuthResponseDto loginUser(LoginUserDto request);

}
