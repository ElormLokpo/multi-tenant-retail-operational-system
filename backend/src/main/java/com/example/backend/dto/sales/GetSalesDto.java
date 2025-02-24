package com.example.backend.dto.sales;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import com.example.backend.models.product.CurrencyEnum;
import com.example.backend.models.sales.SaleItem;
import com.example.backend.models.shops.ShopModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSalesDto {
    public UUID id;

    public Collection<SaleItem> saleItems;

    public CurrencyEnum currencyEnum;

    public Double totalPrice;

    public Date timestamp;

    public ShopModel shop;
}
