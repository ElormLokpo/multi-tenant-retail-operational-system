package com.example.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.dto.product.SalesProductDto;
import com.example.backend.dto.sales.InitSalesDto;
import com.example.backend.dto.sales.SalesResponseDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.mappers.SalesMapper;
import com.example.backend.mappers.ProductMapper;

import com.example.backend.models.inventory.InventoryModel;
import com.example.backend.models.product.ProductModel;
import com.example.backend.models.product.ProductPricing;
import com.example.backend.models.sales.SaleItem;
import com.example.backend.models.sales.SalesModel;
import com.example.backend.models.shops.ShopModel;
import com.example.backend.repositories.InventoryRepository;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.repositories.SalesRepository;
import com.example.backend.repositories.ShopRepository;

@Service
public class SalesService {

    SalesRepository salesRepository;
    InventoryRepository inventoryRepository;
    ShopRepository shopRepository;
    ProductRepository productRepository;

    public SalesService(SalesRepository _salesRepository, InventoryRepository _inventoryRepository,
            ShopRepository _shopRepository, ProductRepository _productRepository) {
        this.salesRepository = _salesRepository;
        this.inventoryRepository = _inventoryRepository;
        this.shopRepository = _shopRepository;
        this.productRepository = _productRepository;

    }

    public SalesResponseDto InitiateSales(InitSalesDto saleRequest, UUID shopId) {
        ShopModel shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id:" + shopId + "not found"));

        Collection<InventoryModel> allInventory = inventoryRepository.findInventoryByShopN(shopId);

        Double totalCostInc = 0d;

        saleRequest.setShop(shop);

        List<SaleItem> saleItemsConv = new ArrayList<>(saleRequest.getSaleItems());
        List<ProductModel> productsNotInInventory = new ArrayList<>();

        for (SaleItem saleItem : saleItemsConv) {

            Boolean productExists = productRepository.existsById(saleItem.getProductId());

            if (productExists) {
                InventoryModel productFoundInventory = allInventory.stream()
                        .filter(inventory -> inventory.getProductId()
                                .equals(saleItem.getProductId()))
                        .findFirst().orElse(null);

                if (productFoundInventory != null) {
                    ProductModel productDetail = productRepository.findById(productFoundInventory.getProductId())
                            .orElse(null);

                    List<ProductPricing> productPricings = new ArrayList<>(productDetail.getPricing());

                    // Since two currencies cannot have the same uint price...consider using a no
                    // duplicate data structure...in product Pricing...
                    ProductPricing productPrice = productPricings.stream()
                            .filter(pricing -> pricing.getProductCurrency().equals(saleRequest.getCurrency()))
                            .findFirst()
                            .orElse(null);

                    totalCostInc += productPrice.getUnitPrice();

                    productFoundInventory.setQtyInStock(productFoundInventory.getQtyInStock() - 1);
                    inventoryRepository.save(productFoundInventory);

                } else {
                    productsNotInInventory.add(productRepository.findById(saleItem.getProductId()).orElse(null));
                    continue;
                }
            } else {
                continue;
            }

        }

        SalesModel salesToModel = SalesMapper.INSTANCE.salesDtoToModel(saleRequest);
        salesToModel.setTotalPrice(totalCostInc);

        SalesModel finalSale = salesRepository.save(salesToModel);

        Collection<SalesProductDto> productsNotInShop = productsNotInInventory.stream()
                .map(productN -> ProductMapper.INSTANCE.productToSalesDto(productN)).collect(Collectors.toList());

        SalesResponseDto salesResponse = SalesResponseDto.builder()
                .sale(finalSale)
                .productsNotInShop(productsNotInShop)
                .build();

        return salesResponse;

    }
}
