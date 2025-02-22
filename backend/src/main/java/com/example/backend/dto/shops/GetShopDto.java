package com.example.backend.dto.shops;

import java.util.UUID;

import com.example.backend.models.franchise.FranchiseModel;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GetShopDto {
    public UUID id;
    public String shopName;
    public String shopLocation;
    public FranchiseModel franchise;
}
