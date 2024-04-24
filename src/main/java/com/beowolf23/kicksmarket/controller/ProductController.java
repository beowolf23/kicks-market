package com.beowolf23.kicksmarket.controller;
import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.exception.ProductNotFoundException;
import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.model.dto.ApiPageableResponse;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.product.CreateProductRequest;
import com.beowolf23.kicksmarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products")
    @GetMapping("/products")
    public ResponseEntity<ApiPageableResponse<Product>> getProducts(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int pageNumber,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int pageSize
    ) {
        ApiPageableResponse<Product> response = productService.getAllProductsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/products/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProduct(
            @Parameter(description = "Product ID") @PathVariable Long productId
    ) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @Operation(summary = "Add a new product")
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> addProduct(
            @Parameter(description = "Product details") @RequestBody CreateProductRequest product
    ) throws DatabaseInsertionException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }
}
