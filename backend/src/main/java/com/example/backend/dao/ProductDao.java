package com.example.backend.dao;

import java.util.UUID;

import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.product.CreateProductDto;
import com.example.backend.dto.product.GetProductDto;

public interface ProductDao {
    public GetResponseDto getAllProducts(int pageSize, int pageNo, String sortBy, String sortDir);

    public GetProductDto getProduct(UUID id);

    public GetProductDto createProduct(CreateProductDto product);

    public GetProductDto deleteProduct(UUID id);
}
