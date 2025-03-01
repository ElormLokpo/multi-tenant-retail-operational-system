package com.example.backend.dto.auth;

import com.example.backend.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthResponseDto extends ResponseDto {
    private String accessToken;
    public UserResponseDto data;

}
