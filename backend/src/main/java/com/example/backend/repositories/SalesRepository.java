package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.models.sales.SalesModel;

public interface SalesRepository extends JpaRepository<SalesModel, UUID> {}
