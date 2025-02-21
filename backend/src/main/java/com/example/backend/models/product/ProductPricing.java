package com.example.backend.models.product;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductPricing {
     @Builder.Default
    public Double unitPrice = 0.0;

    @Builder.Default
    public CurrencyEnum productCurrency = CurrencyEnum.GHS;
}
