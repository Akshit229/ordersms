package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }
}
