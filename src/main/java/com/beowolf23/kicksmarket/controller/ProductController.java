package com.beowolf23.kicksmarket.controller;

import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return Collections.emptyList();
    }
}
