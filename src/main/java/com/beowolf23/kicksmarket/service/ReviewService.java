package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.exception.ProductNotFoundException;
import com.beowolf23.kicksmarket.exception.ReviewNotFoundException;
import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.model.dao.Review;
import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.review.CreateReviewRequest;
import com.beowolf23.kicksmarket.repository.ProductRepository;
import com.beowolf23.kicksmarket.repository.ReviewRepository;
import com.beowolf23.kicksmarket.repository.UserRepository;
import com.beowolf23.kicksmarket.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ApiResponse<Review> createReview(CreateReviewRequest reviewRequest) throws DatabaseInsertionException, ProductNotFoundException {

        User user = userRepository.findById(reviewRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Review newReview = Review.builder()
                .user(user)
                .product(product)
                .rating(reviewRequest.getRating())
                .comment(reviewRequest.getComment())
                .createdAt(new Date())
                .build();

        ValidationUtils.validate(newReview);

        try {
            Review review = reviewRepository.save(newReview);
            return ApiResponse.<Review>builder()
                    .data(review)
                    .statusCode(HttpStatus.CREATED.value())
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new DatabaseInsertionException("Failed to create review: " + e.getMessage());
        }
    }

    public ApiResponse<Void> deleteReview(Long reviewId) throws ReviewNotFoundException {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ReviewNotFoundException("Review not found");
        }

        reviewRepository.deleteById(reviewId);
        return ApiResponse.<Void>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .success(true)
                .build();
    }

    public ApiResponse<List<Review>> getAllReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProduct_ProductId(productId);

        return ApiResponse.<List<Review>>builder()
                .data(reviews)
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
