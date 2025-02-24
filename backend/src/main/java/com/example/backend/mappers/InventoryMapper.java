package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.inventory.CreateInventoryDto;
import com.example.backend.dto.inventory.GetInventoryDto;
import com.example.backend.models.inventory.InventoryModel;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    public InventoryModel inventoryDtoToModel(CreateInventoryDto inventoryDto);
    public GetInventoryDto inventoryToDto(InventoryModel inventory);
}
