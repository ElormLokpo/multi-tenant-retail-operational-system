package com.example.backend.dto.sales;

import java.util.Collection;
import com.example.backend.models.product.CurrencyEnum;
import com.example.backend.models.sales.SaleItem;
import com.example.backend.models.shops.ShopModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InitSalesDto {
 
    public Collection<SaleItem> saleItems;
    public CurrencyEnum currencyEnum;
    ShopModel shop;

}
