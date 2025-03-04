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
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.response.ResponseDto;
import com.example.backend.dto.sales.InitSalesDto;
import com.example.backend.services.SalesService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    SalesService salesService;

    public SalesController(SalesService _salesService) {
        this.salesService = _salesService;
    }

    @GetMapping()
    public ResponseEntity<GetResponseDto> getAllSales(
            @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "30", required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "timestamp", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(salesService.getAllSales(pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("shop/{shopId}")
    public ResponseEntity<GetResponseDto> getSalessByShop(
            @PathVariable(value="shopId") UUID shopId,
            @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "30", required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "timestamp", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(salesService.getAllSalesByShop(shopId, pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getSale(@PathVariable UUID id) {

        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Sales query successful")
                .data(salesService.getSale(id))
                .build());
    }

    @PostMapping("create/{shopId}")
    public ResponseEntity<ResponseDto> initiateSale(
            @PathVariable UUID shopId,
            @RequestBody InitSalesDto sales) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Sale successful")
                .data(salesService.initiateSales(sales, shopId))
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteSales(@PathVariable UUID id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Sales deleted successfully")
                .data(salesService.deleteSale(id))
                .build());

    }
}
