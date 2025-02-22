package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.shops.CreateShopDto;
import com.example.backend.dto.shops.GetShopDto;
import com.example.backend.models.shops.ShopModel;

@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    public GetShopDto shopToDto(ShopModel shop);
    public ShopModel shopDtotoModel(CreateShopDto shop);
}
