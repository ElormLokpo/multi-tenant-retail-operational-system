package com.example.backend.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.dao.ProductDao;
import com.example.backend.dto.product.CreateProductDto;
import com.example.backend.dto.product.GetProductDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.mappers.ProductMapper;
import com.example.backend.models.product.ProductModel;
import com.example.backend.repositories.ProductRepository;

@Service
public class ProductService implements ProductDao {

    ProductRepository productRepository;

    public ProductService(ProductRepository _productRepository) {
        this.productRepository = _productRepository;
    }

    @Override
    public GetResponseDto getAllProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ProductModel> productPage = productRepository.findAll(pageable);

        Collection<ProductModel> pageContent = productPage.getContent();
        Collection<GetProductDto> productList = pageContent.stream()
                .map(product -> ProductMapper.INSTANCE.productToDto(product)).collect(Collectors.toList());

        return GetResponseDto.builder()
                .success(true)
                .message("Product query successful")
                .data(productList)
                .pageNo(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalNoElements(productPage.getNumberOfElements())
                .totalNoPages(productPage.getTotalPages())
                .isLastPage(productPage.isLast())
                .build();
    }

    @Override
    public GetProductDto getProduct(UUID id) {
        return ProductMapper.INSTANCE.productToDto(
                productRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product with id:" + id + " not found.")));
    }

    @Override
    public GetProductDto createProduct(CreateProductDto product) {
        return ProductMapper.INSTANCE.productToDto(
                productRepository.save(ProductMapper.INSTANCE.productDtoToModel(product)));
    }

    @Override
    public GetProductDto deleteProduct(UUID id) {
        ProductModel productFound = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id:" + id + " not found."));

        productRepository.deleteById(id);

        return ProductMapper.INSTANCE.productToDto(productFound);
    }

}
