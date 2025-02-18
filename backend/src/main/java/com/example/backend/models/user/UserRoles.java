package com.example.backend.models.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static com.example.backend.models.user.UserPermissions.OWNER_CRUD;
import static com.example.backend.models.user.UserPermissions.ATTENDANT_CRUD;
import static com.example.backend.models.user.UserPermissions.INVENTORY_CRUD;


@RequiredArgsConstructor
public enum UserRoles {
    OWNER(Set.of(OWNER_CRUD, ATTENDANT_CRUD, INVENTORY_CRUD)),
    ATTENDANT(Set.of(ATTENDANT_CRUD)),
    INVENTORY_MANAGER(Set.of(INVENTORY_CRUD));

    @Getter
    private final Set<UserPermissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getUserPermission()))
        .collect(Collectors.toList());
    }
}
