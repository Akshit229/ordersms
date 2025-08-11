package com.quicken.ordersms.servicesImpl;

import com.quicken.ordersms.dtos.ProductDTO;
import com.quicken.ordersms.entities.Product;
import com.quicken.ordersms.exceptions.BadRequestException;
import com.quicken.ordersms.mappers.ProductMapper;
import com.quicken.ordersms.repositories.ProductRepository;
import com.quicken.ordersms.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDto) {
        Product product = productMapper.toProduct(productDto);
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new BadRequestException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Product price must be greater than zero");
        }
        boolean exists = productRepository
                .findByNameAndPrice(product.getName().trim(), product.getPrice())
                .isPresent();

        if (exists) {
            throw new BadRequestException("Product with the same name and price already exists");
        }

        Product savedProduct =  productRepository.save(product);
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product p =  productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return productMapper.toProductDto(p);
    }
}
