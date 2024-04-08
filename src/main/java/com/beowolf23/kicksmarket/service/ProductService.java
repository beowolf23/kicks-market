package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }


}
