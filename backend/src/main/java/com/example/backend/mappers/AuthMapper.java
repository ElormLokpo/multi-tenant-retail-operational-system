package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.auth.RegisterUserDto;
import com.example.backend.dto.auth.UserResponseDto;
import com.example.backend.models.user.UserModel;

@Mapper
public interface AuthMapper {
    public AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    public UserModel userDtoToModel(RegisterUserDto registerUserDto);
    public UserResponseDto userModelToDto(UserModel user);

}
