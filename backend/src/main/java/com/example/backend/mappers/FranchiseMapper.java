package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.franchise.CreateFranchiseDto;
import com.example.backend.dto.franchise.GetFranchiseDto;
import com.example.backend.models.franchise.FranchiseModel;

@Mapper
public interface FranchiseMapper {
    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    FranchiseModel franchiseDtoToModel(CreateFranchiseDto franchiseDto);
    GetFranchiseDto franchiseToDto(FranchiseModel franchise);
}
