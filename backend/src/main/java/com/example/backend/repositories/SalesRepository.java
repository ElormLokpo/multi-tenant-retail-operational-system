package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.sales.SalesModel;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, UUID> {
    @Query("SELECT s FROM sales s WHERE s.shop.id = :shopid")
    Page<SalesModel> findSalesByShop(UUID shopid, Pageable pageable);
}
