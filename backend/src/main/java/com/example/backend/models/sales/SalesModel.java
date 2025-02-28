package com.example.backend.models.sales;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.product.CurrencyEnum;
import com.example.backend.models.shops.ShopModel;

import jakarta.persistence.Embedded;
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
    public CurrencyEnum currency = CurrencyEnum.GHS;

    @Builder.Default
    public Double totalPrice = 0.0;

    @Builder.Default
    public Date timestamp = new Date();

    @ManyToOne
    @JoinColumn(name="shop_id")
    public ShopModel shop;

}
