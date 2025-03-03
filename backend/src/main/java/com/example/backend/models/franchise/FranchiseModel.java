package com.example.backend.models.franchise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.shops.ShopModel;
import com.example.backend.models.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "franchise")
@Builder
public class FranchiseModel {

    @GeneratedValue
    @Id
    public UUID id;

    public String franchiseName;
    public String mainLocation;

    @OneToOne
    @JoinColumn(name = "owner_id", unique = true, nullable = false)
    public UserModel owner;


    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY, orphanRemoval = true)
    public Collection<UserModel> workers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    public Collection<ShopModel> shops = new ArrayList<>();
}
