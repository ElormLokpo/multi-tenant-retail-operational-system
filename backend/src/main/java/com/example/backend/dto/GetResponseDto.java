package com.example.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetResponseDto {
    public boolean success;
    public String message;
    public Object data;
    public int pageSize;
    public int pageNo;
    public int totalNoPages;
    public int totalNoElements;
    public boolean isLastPage;
}
