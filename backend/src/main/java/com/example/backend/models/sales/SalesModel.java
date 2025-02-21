package com.example.backend.models.sales;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.product.CurrencyEnum;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sales")
@Builder
public class SalesModel {
    @GeneratedValue
    @Id
    public UUID id;

    @Embedded
    @Builder.Default
    public Collection<SaleItem> saleItems = new ArrayList<>();

    @Builder.Default
    public CurrencyEnum currencyEnum = CurrencyEnum.GHS;

    @Builder.Default
    public Double totalPrice = 0.0;

    @Builder.Default
    public Date timestamp = new Date();

}
