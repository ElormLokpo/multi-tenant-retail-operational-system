package com.example.backend.models.user;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="users")
public class UserModel {
    
    @GeneratedValue
    @Id
    public UUID id;

    public String username;
    public String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    public UserRoles role = UserRoles.OWNER;

}
