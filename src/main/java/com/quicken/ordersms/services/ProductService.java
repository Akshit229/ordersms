package com.quicken.ordersms.services;

import com.quicken.ordersms.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId);
}
