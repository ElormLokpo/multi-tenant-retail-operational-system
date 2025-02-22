package com.example.backend.dto.franchise;

import java.util.UUID;

import com.example.backend.models.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFranchiseDto {
     public UUID id;
    public String franchiseName;
    public String mainLocation;
    public UserModel owner;

}
