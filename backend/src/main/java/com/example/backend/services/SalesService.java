package com.example.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.backend.dto.sales.InitSalesDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.models.inventory.InventoryModel;
import com.example.backend.models.product.ProductModel;
import com.example.backend.models.sales.SaleItem;
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

    public Boolean InitiateSales(InitSalesDto saleRequest, UUID shopId) {
        ShopModel shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id:" + shopId + "not found"));

        Collection<InventoryModel> allInventory = inventoryRepository.findInventoryByShopN(shopId);

        Double totalCostInc = 0d;

        saleRequest.setShop(shop);

        List<SaleItem> saleItemsConv = new ArrayList<>(saleRequest.getSaleItems());

        for (SaleItem saleItem : saleItemsConv) {
            // Implement checks

            // Checking if sale item(product) exists in inventory

            InventoryModel productFoundInventory = allInventory.stream().filter(inventory -> inventory.productId
                    .equals(saleItem.getProductId())).findFirst().orElse(null);
        }

        // Loop throw sale items
        // update inventory of product.

        return true;
    }
}
