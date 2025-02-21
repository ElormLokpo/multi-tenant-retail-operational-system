package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.models.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
    
}
