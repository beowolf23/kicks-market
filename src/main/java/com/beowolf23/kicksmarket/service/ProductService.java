package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.exception.ProductNotFoundException;
import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.model.dto.ApiPageableResponse;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.product.CreateProductRequest;
import com.beowolf23.kicksmarket.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ApiResponse<Product> getProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product is missing"));

        return ApiResponse.<Product>builder()
                .data(product)
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public ApiPageableResponse<Product> getAllProductsWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> page = productRepository.findAll(pageable);
        return new ApiPageableResponse<>(page, HttpStatus.CREATED.value(), true);
    }

    public List<Product> getAllProductsWithPaginationAndSorting(int pageNumber,
                                                                int pageSize,
                                                                String sortBy,
                                                                String direction) {
        return null;
    }

    public List<Product> getAvailableProductQuantities(Set<Long> productsIds) {
        return productRepository.findAllById(productsIds);
    }

    public ApiResponse<Product> addProduct(CreateProductRequest productRequest) throws DatabaseInsertionException {
        Product newProduct = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .brandName(productRequest.getBrandName())
                .quantityAvailable(productRequest.getQuantityAvailable())
                .build();

        try {
            Product product = productRepository.save(newProduct);
            return ApiResponse.<Product>builder()
                    .success(true)
                    .statusCode(HttpStatus.CREATED.value())
                    .data(product)
                    .build();
        } catch (Exception exception) {
            throw new DatabaseInsertionException("Product insertion failed: " + exception.getMessage());
        }
    }
}
