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
import com.example.backend.dto.shops.CreateShopDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.response.ResponseDto;
import com.example.backend.services.ShopService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    ShopService shopService;

    public ShopController(ShopService _shopService) {
        this.shopService = _shopService;
    }

    @GetMapping()
    public ResponseEntity<GetResponseDto> getAllShops(
            @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "30", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "shopName", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(shopService.getAllShops(pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("franchise/{franchiseId}")
    public ResponseEntity<GetResponseDto> getShopsByFranchise(
            @PathVariable("franchiseId") UUID franchiseId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "30", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "shopName", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(shopService.getShopsByFranchise(franchiseId, pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getShop(@PathVariable UUID id) {

        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Shop query successful")
                .data(shopService.getShop(id))
                .build());
    }

    @PostMapping("create/{franchiseId}")
    public ResponseEntity<ResponseDto> createShop(
            @PathVariable UUID franchiseId,
            @RequestBody CreateShopDto shop) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Shop created successfully")
                .data(shopService.createShop(franchiseId, shop))
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteShop(@PathVariable UUID id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Shop deleted successfully")
                .data(shopService.deleteShop(id))
                .build());

    }
}
