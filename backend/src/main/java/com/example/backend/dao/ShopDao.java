package com.example.backend.dao;

import java.util.UUID;
import com.example.backend.dto.shops.CreateShopDto;
import com.example.backend.dto.shops.GetShopDto;
import com.example.backend.dto.response.GetResponseDto;

public interface ShopDao {
    public GetResponseDto getAllShops(int pageSize, int pageNo, String sortBy, String sortDir);

    public GetResponseDto getShopsByFranchise(UUID id, int pageSize, int pageNo, String sortBy, String sortDir);

    public GetShopDto getShop(UUID id);

    public GetShopDto createShop(CreateShopDto shops);

    public GetShopDto deleteShop(UUID id);
}
