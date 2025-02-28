package com.example.backend.dao;

import java.util.UUID;

import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.sales.InitSalesDto;
import com.example.backend.dto.sales.SalesResponseDto;
import com.example.backend.models.sales.SalesModel;

public interface SalesDao {
    public SalesResponseDto initiateSales(InitSalesDto saleRequest, UUID shopId);

    public GetResponseDto getAllSales(int pageSize, int pageNo, String sortBy, String sortDir);

    public GetResponseDto getAllSalesByShop(UUID shopid,int pageSize, int pageNo, String sortBy, String sortDir);

    public SalesModel getSale(UUID id);

    public SalesModel deleteSale(UUID id);

}
