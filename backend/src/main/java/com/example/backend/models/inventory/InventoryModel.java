package com.example.backend.models.inventory;

import java.util.UUID;

import com.example.backend.models.shops.ShopModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "inventory")
public class InventoryModel {

    @GeneratedValue
    @Id
    public UUID id;
    public UUID productId;
    public String productName;

    @Builder.Default
    public Integer qtyInStock = 0;

    @Builder.Default
    public Integer restockLevel = 10;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    public ShopModel shop;
}
