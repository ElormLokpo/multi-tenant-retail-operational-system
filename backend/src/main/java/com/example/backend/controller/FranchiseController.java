package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dto.franchise.CreateFranchiseDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.response.ResponseDto;
import com.example.backend.services.FranchiseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {

    FranchiseService franchiseService;

    public FranchiseController(FranchiseService _franchiseService) {
        this.franchiseService = _franchiseService;
    }

    @GetMapping
    public ResponseEntity<GetResponseDto> getAllFranchise(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "30", required = false ) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "franchiseName", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(franchiseService.getAllFranchise(pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("owner/{ownerId}")
    public ResponseEntity<GetResponseDto> getAllFranchiseByUser(
            @PathVariable UUID ownerId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "30", required = false ) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "franchiseName", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(franchiseService.getFranchiseByUser(ownerId, pageSize, pageNo, sortBy, sortDir));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getFranchise(@PathVariable UUID id) {

        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Franchise query successful")
                .data(franchiseService.getFranchise(id))
                .build());
    }

    @PostMapping("create/{ownerId}")
    public ResponseEntity<ResponseDto> createFranchise(
            @PathVariable UUID ownerId,
            @RequestBody CreateFranchiseDto franchise) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Franchise created successfully")
                .data(franchiseService.createFranchise(ownerId, franchise))
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteFranchise(@PathVariable UUID id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .success(true)
                .message("Franchise deleted successfully")
                .data(franchiseService.deleteFranchise(id))
                .build());

    }
}
