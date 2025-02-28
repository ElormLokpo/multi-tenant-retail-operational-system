package com.example.backend.dto.product;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesProductDto {
    public UUID id;
    public String productName;
}
