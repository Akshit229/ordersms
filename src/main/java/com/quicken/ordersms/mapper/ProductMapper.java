package com.quicken.ordersms.mapper;

import com.quicken.ordersms.dtos.OrderDTO;
import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Order;
import com.quicken.ordersms.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Product product);
    Product toProduct(ProductDTO dto);
}
