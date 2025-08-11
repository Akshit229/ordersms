package com.quicken.ordersms.mappers;

import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Product product);
    Product toProduct(ProductDTO dto);
}
