package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.sales.InitSalesDto;
import com.example.backend.models.sales.SalesModel;

@Mapper
public interface SalesMapper {
    SalesMapper INSTANCE = Mappers.getMapper(SalesMapper.class);

    public SalesModel salesDtoToModel(InitSalesDto salesDto);

}
