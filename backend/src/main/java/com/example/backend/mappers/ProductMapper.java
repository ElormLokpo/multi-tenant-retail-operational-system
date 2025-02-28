package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dto.product.CreateProductDto;
import com.example.backend.dto.product.GetProductDto;
import com.example.backend.dto.product.SalesProductDto;
import com.example.backend.models.product.ProductModel;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    public GetProductDto productToDto(ProductModel product);
    public ProductModel productDtoToModel(CreateProductDto productDto); 

    public SalesProductDto productToSalesDto(ProductModel product);
}
