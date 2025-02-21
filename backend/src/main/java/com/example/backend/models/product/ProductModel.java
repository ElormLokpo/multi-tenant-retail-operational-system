package com.example.backend.models.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    @GeneratedValue
    @Id
    public UUID id;
    public String productName;
    public String productDescription;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    public Collection<ProductCategroyEnum> productCategories = new ArrayList<>();

    @Embedded
    @Builder.Default
    public Collection<ProductPricing> pricing = new ArrayList<>();

    public String barCode;

    @Builder.Default
    public Boolean isFragile = false;
    @Builder.Default
    public Boolean isPerishable = false;

    public Date expirationDate;

    @Builder.Default
    public Collection<String> imgUrls = new ArrayList<>();

    @Builder.Default
    public Date dateAdded = new Date();

  
}
