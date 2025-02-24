package com.example.backend.dao;

import java.util.UUID;

import com.example.backend.dto.inventory.CreateInventoryDto;
import com.example.backend.dto.inventory.GetInventoryDto;
import com.example.backend.dto.response.GetResponseDto;

public interface InventoryDao {
    public GetResponseDto getAllInventoryByShop(UUID shopId, int pageSize, int pageNo, String sortBy, String sortDir);

    public GetInventoryDto getInventory(UUID id);

    public GetInventoryDto createInventory(UUID shopId, CreateInventoryDto Inventory);

    public GetInventoryDto deleteInventory(UUID id);
}
