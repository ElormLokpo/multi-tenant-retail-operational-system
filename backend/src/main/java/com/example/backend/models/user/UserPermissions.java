package com.example.backend.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserPermissions {
    OWNER_CRUD("owner:crud"),
    ATTENDANT_CRUD("attendant:crud"),
    INVENTORY_CRUD("inventory:crud");
  
    
    @Getter
    private final String userPermission;
}

