package com.example.backend.dao;

import java.util.UUID;
import com.example.backend.dto.franchise.CreateFranchiseDto;
import com.example.backend.dto.franchise.GetFranchiseDto;
import com.example.backend.dto.response.GetResponseDto;

public interface FranchiseDao {
    public GetResponseDto getAllFranchise(int pageSize, int pageNo, String sortBy, String sortDir);

    public GetResponseDto getFranchiseByUser(UUID ownerId, int pageSize, int pageNo, String sortBy, String sortDir);

    public GetFranchiseDto getFranchise(UUID id);

    public GetFranchiseDto createFranchise(UUID ownerId, CreateFranchiseDto franchise);

    public GetFranchiseDto deleteFranchise(UUID id);

}
