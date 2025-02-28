package com.example.backend.dto.sales;

import java.util.Collection;

import com.example.backend.dto.product.SalesProductDto;
import com.example.backend.models.sales.SalesModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesResponseDto {
    SalesModel sale;
    Collection<SalesProductDto> productsNotInShop;
}