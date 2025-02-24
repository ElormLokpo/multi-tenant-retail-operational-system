package com.example.backend.dto.inventory;

import java.util.UUID;

import com.example.backend.models.shops.ShopModel;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateInventoryDto {
    public UUID productId;
    public String productName;
    public Integer qtyInStock;
    public Integer restockLevel;
    public ShopModel shop;
}
