package com.example.backend.models.user;

import java.util.UUID;
import com.example.backend.models.franchise.FranchiseModel;
import com.example.backend.models.shops.ShopModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserModel {

    @GeneratedValue
    @Id
    public UUID id;

    public String username;
    public String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    public UserRoles role = UserRoles.OWNER;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    public FranchiseModel franchise;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    public ShopModel shop;

}
