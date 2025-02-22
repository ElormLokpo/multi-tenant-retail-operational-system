package com.example.backend.dto.franchise;

import com.example.backend.models.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFranchiseDto {

    public String franchiseName;
    public String mainLocation;
    public UserModel owner;

}
