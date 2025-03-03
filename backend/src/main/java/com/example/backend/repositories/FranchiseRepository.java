package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.franchise.FranchiseModel;

@Repository
public interface FranchiseRepository extends JpaRepository<FranchiseModel, UUID> {
    @Query("SELECT f from franchise f WHERE f.owner = :ownerId")
    Page<FranchiseModel> findAllFranchiseByUser(UUID ownerId, Pageable pageable);

}
