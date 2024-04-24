package com.beowolf23.kicksmarket.repository;

import com.beowolf23.kicksmarket.model.dao.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProduct_ProductId(Long productId);
}
