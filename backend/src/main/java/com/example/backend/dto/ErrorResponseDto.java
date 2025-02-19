package com.example.backend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponseDto {
    public boolean success;
    public String message;
    public Object data;
}
