package com.example.backend.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponseGen {
    public boolean success;
    public String message;
    public Object data;
}
