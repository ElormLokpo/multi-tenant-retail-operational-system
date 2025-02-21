package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.models.shops.ShopModel;

public interface ShopsRepository extends JpaRepository<ShopModel, UUID> {

}
