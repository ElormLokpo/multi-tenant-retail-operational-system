package com.example.backend.dto.shops;

import com.example.backend.models.franchise.FranchiseModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateShopDto {
    
    public String shopName;
    public String shopLocation;
    public FranchiseModel franchise;

}
