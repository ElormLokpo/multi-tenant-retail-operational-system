package com.example.backend.models.shops;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.franchise.FranchiseModel;
import com.example.backend.models.inventory.InventoryModel;
import com.example.backend.models.user.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shops")
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

    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, orphanRemoval = true)
    public Collection<UserModel> shopWorkers = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, orphanRemoval = true)
    public Collection<InventoryModel> inventory = new ArrayList<>();
}
