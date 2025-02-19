package com.example.backend.dto.auth;

import com.example.backend.models.user.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDto {
    public String username;
    public String password;
    public UserRoles role;
}
