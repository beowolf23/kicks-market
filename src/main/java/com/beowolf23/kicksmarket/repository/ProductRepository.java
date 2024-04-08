package com.beowolf23.kicksmarket.repository;

import com.beowolf23.kicksmarket.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
