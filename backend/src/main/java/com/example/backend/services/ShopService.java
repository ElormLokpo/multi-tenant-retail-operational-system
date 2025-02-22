package com.example.backend.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.backend.dao.ShopDao;
import com.example.backend.dto.shops.GetShopDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.dto.shops.CreateShopDto;
import com.example.backend.mappers.ShopMapper;
import com.example.backend.repositories.FranchiseRepository;
import com.example.backend.repositories.ShopRepository;
import com.example.backend.models.franchise.FranchiseModel;
import com.example.backend.models.shops.ShopModel;

@Service
public class ShopService implements ShopDao {
    ShopRepository shopRepository;
    FranchiseRepository franchiseRepository;

    public ShopService(ShopRepository _shopRepository, FranchiseRepository _franchiseRepository) {
        this.shopRepository = _shopRepository;
        this.franchiseRepository = _franchiseRepository;
    }

    @Override
    public GetResponseDto getAllShops(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ShopModel> shopPage = shopRepository.findAll(pageable);

        Collection<ShopModel> pageContent = shopPage.getContent();

        Collection<GetShopDto> shopList = pageContent.stream()
                .map(shop -> ShopMapper.INSTANCE.shopToDto(shop))
                .collect(Collectors.toList());

        return GetResponseDto.builder()
                .success(true)
                .message("Shop query successful")
                .data(shopList)
                .pageSize(shopPage.getSize())
                .pageNo(shopPage.getNumber())
                .totalNoPages(shopPage.getTotalPages())
                .totalNoElements(shopPage.getNumberOfElements())
                .isLastPage(shopPage.isLast())
                .build();
    }

    @Override
    public GetResponseDto getShopsByFranchise(UUID id, int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ShopModel> shopPage = shopRepository.findShopsByFranchise(id, pageable);

        Collection<ShopModel> pageContent = shopPage.getContent();

        Collection<GetShopDto> shopList = pageContent.stream()
                .map(shop -> ShopMapper.INSTANCE.shopToDto(shop))
                .collect(Collectors.toList());

        return GetResponseDto.builder()
                .success(true)
                .message("Shop query successful")
                .data(shopList)
                .pageSize(shopPage.getSize())
                .pageNo(shopPage.getNumber())
                .totalNoPages(shopPage.getTotalPages())
                .totalNoElements(shopPage.getNumberOfElements())
                .isLastPage(shopPage.isLast())
                .build();
    }

    @Override
    public GetShopDto getShop(UUID id) {
        return ShopMapper.INSTANCE.shopToDto(shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id:" + id + " not found")));
    }

    @Override
    public GetShopDto createShop(CreateShopDto shops) {
        FranchiseModel franchiseModel = franchiseRepository.findById(shops.getFranchise().id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Franchise with id:" + shops.getFranchise().id + " not found"));

        shops.setFranchise(franchiseModel);
        return ShopMapper.INSTANCE.shopToDto(shopRepository.save(ShopMapper.INSTANCE.shopDtotoModel(shops)));

    }

    @Override
    public GetShopDto deleteShop(UUID id) {
        ShopModel shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id:" + id + " not found"));

        shopRepository.deleteById(id);

        return ShopMapper.INSTANCE.shopToDto(shop);

    }

}
