package com.beowolf23.kicksmarket.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;


}
