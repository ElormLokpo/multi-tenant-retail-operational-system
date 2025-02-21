package com.example.backend.models.shops;

import java.util.UUID;

import com.example.backend.models.franchise.FranchiseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="shops")
@Builder
public class ShopModel {
    
    @GeneratedValue
    @Id
    public UUID id;

    public String shopName;
    public String shopLocation;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    public FranchiseModel franchise;
}
