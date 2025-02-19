package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.backend.dao.AuthDao;
import com.example.backend.dto.auth.AuthResponseDto;
import com.example.backend.dto.auth.LoginUserDto;
import com.example.backend.dto.auth.RegisterUserDto;

import com.example.backend.exceptions.AuthException;
import com.example.backend.mappers.AuthMapper;
import com.example.backend.models.user.UserModel;
import com.example.backend.repositories.UserRepository;

@Service
public class AuthService implements AuthDao {
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    public AuthService(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    @Override
    public AuthResponseDto registerUser(RegisterUserDto request) throws AuthException {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new AuthException("Username or password field cannot be null");
        }

        UserModel userFound = userRepository.findByUsername(request.getUsername());
        if (userFound != null) {
            throw new AuthException("User with username: " + request.getUsername() + " already exists");
        }
        UserModel userCreated = userRepository.save(AuthMapper.INSTANCE.userDtoToModel(request));

        return AuthResponseDto.builder()
                .success(true)
                .message("User registered successfully.")
                .data(AuthMapper.INSTANCE.userModelToDto(userCreated))
                .accessToken(jwtService.generateToken(userCreated.getUsername()))
                .build();
    }

    @Override
    public AuthResponseDto loginUser(LoginUserDto request) throws AuthException {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new AuthException("Username or password field cannot be null");
        }

        UserModel userFound = userRepository.findByUsername(request.getUsername());
        if (userFound != null) {
            throw new AuthException("User with username: " + request.getUsername() + " does NOT exist");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated() == false) {
            throw new AuthException("Incorrect password.");
        }

        return AuthResponseDto.builder()
                .success(true)
                .message("User login successful.")
                .data(AuthMapper.INSTANCE.userModelToDto(userFound))
                .accessToken(jwtService.generateToken(request.getUsername()))
                .build();
    }

}
