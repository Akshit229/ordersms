package com.quicken.ordersms.services;

import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Product;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO product);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long productId);
}
