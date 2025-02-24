package com.example.backend.repositories;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.backend.models.inventory.InventoryModel;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, UUID> {
    @Query("SELECT i FROM inventory i WHERE i.shop.id = :shopId")
    Page<InventoryModel> findInventoryByShop(UUID shopId, Pageable pageable);
}
