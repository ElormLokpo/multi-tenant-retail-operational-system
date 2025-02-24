package com.example.backend.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.dao.InventoryDao;
import com.example.backend.dto.inventory.CreateInventoryDto;
import com.example.backend.dto.inventory.GetInventoryDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.mappers.InventoryMapper;
import com.example.backend.models.inventory.InventoryModel;
import com.example.backend.models.product.ProductModel;
import com.example.backend.models.shops.ShopModel;
import com.example.backend.repositories.InventoryRepository;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.repositories.ShopRepository;

@Service
public class InventoryService implements InventoryDao {

    InventoryRepository inventoryRepository;
    ShopRepository shopRepository;
    ProductRepository productRepository;
    
    public InventoryService(InventoryRepository _inventoryRepository, ShopRepository _shopRepository, ProductRepository _productRepository) {
        this.inventoryRepository = _inventoryRepository;
        this.shopRepository = _shopRepository;
        this.productRepository = _productRepository;
    }

    @Override
    public GetResponseDto getAllInventoryByShop(UUID shopId, int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<InventoryModel> inventoryPage = inventoryRepository.findInventoryByShop(shopId, pageable);

        Collection<InventoryModel> pageContent = inventoryPage.getContent();
        Collection<GetInventoryDto> inventoryList = pageContent.stream()
                .map(inventory -> InventoryMapper.INSTANCE.inventoryToDto(inventory)).collect(Collectors.toList());

        return GetResponseDto.builder()
                .success(true)
                .message("Inventory query successful")
                .data(inventoryList)
                .pageNo(inventoryPage.getNumber())
                .pageSize(inventoryPage.getSize())
                .totalNoElements(inventoryPage.getNumberOfElements())
                .totalNoPages(inventoryPage.getTotalPages())
                .isLastPage(inventoryPage.isLast())
                .build();
    }

    @Override
    public GetInventoryDto getInventory(UUID id) {
        return InventoryMapper.INSTANCE.inventoryToDto(inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with id:" + id + " not found.")));
    }

    @Override
    public GetInventoryDto createInventory(UUID shopId, CreateInventoryDto inventory) {
        ShopModel shopModel = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id:" + shopId + " not found."));

        ProductModel productModel = productRepository.findById(inventory.productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product with id:" + inventory.productId + " not found."));;

        inventory.setShop(shopModel);
        inventory.setProductName(productModel.getProductName());
        
        return (InventoryMapper.INSTANCE
                .inventoryToDto(inventoryRepository.save(InventoryMapper.INSTANCE.inventoryDtoToModel(inventory))));

    }

    @Override
    public GetInventoryDto deleteInventory(UUID id) {
    InventoryModel inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with id:" + id + " not found."));

        inventoryRepository.deleteById(id);

        return InventoryMapper.INSTANCE.inventoryToDto(inventory);
    }

}
