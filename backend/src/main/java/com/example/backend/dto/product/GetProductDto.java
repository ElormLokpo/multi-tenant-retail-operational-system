package com.example.backend.dto.product;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import com.example.backend.models.product.ProductCategroyEnum;
import com.example.backend.models.product.ProductPricing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductDto {

    public UUID id;
    public String productName;
    public String productDescription;

    public Collection<ProductCategroyEnum> productCategories;

    public Collection<ProductPricing> pricing;

    public String barCode;

    public Boolean isFragile;
    public Boolean isPerishable;

    public Date expirationDate;

    public Collection<String> imgUrls;
}
