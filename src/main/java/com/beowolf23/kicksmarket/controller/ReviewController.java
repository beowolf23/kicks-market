package com.beowolf23.kicksmarket.controller;

import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.exception.ProductNotFoundException;
import com.beowolf23.kicksmarket.exception.ReviewNotFoundException;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.review.CreateReviewRequest;
import com.beowolf23.kicksmarket.model.dao.Review;
import com.beowolf23.kicksmarket.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createReview(@RequestBody CreateReviewRequest reviewRequest) throws DatabaseInsertionException, ProductNotFoundException {
        ApiResponse<Review> response = reviewService.createReview(reviewRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long reviewId) throws ReviewNotFoundException {
        ApiResponse<Void> response = reviewService.deleteReview(reviewId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<Review>>> getAllReviewsByProductId(@PathVariable Long productId) {
        ApiResponse<List<Review>> response = reviewService.getAllReviewsByProductId(productId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}