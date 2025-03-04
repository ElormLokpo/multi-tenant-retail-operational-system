package com.example.backend.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.inventory.CreateInventoryDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.response.ResponseDto;
import com.example.backend.services.InventoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    public InventoryService inventoryService;

    public InventoryController(InventoryService _inventoryService) {
        this.inventoryService = _inventoryService;
    }

    @GetMapping("shop/{shopId}")
    public ResponseEntity<GetResponseDto> getAllInventoryByShop(
            @PathVariable("shopId") UUID shopId,
            @RequestParam(value="pageNo", defaultValue = "0", required=false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "30", required=false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "productName", required=false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required=false) String sortDir

    ) {
        return ResponseEntity.ok(inventoryService.getAllInventoryByShop(shopId, pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getInventory(@PathVariable UUID id) {

        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Inventory query successful")
                .data(inventoryService.getInventory(id))
                .build());
    }

    @PostMapping("create/{shopId}")
    public ResponseEntity<ResponseDto> createInventory(
            @PathVariable UUID shopId,
            @RequestBody CreateInventoryDto inventory) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Inventory created successfully")
                .data(inventoryService.createInventory(shopId,inventory))
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteInventory(@PathVariable UUID id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Inventory deleted successfully")
                .data(inventoryService.deleteInventory(id))
                .build());

    }
}
