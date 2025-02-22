package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.shops.ShopModel;

@Repository
public interface ShopRepository extends JpaRepository<ShopModel, UUID> {
    @Query("SELECT s from shops s WHERE s.franchise.id = :franchiseId")
    Page<ShopModel> findShopsByFranchise(UUID id, Pageable pageable);
}
