package com.beowolf23.kicksmarket.model.dto.product;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateProductRequest {
    private String productName;
    private String brandName;
    private Double price;
    private Integer quantityAvailable;
}
