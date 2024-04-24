package com.beowolf23.kicksmarket.model.dto.review;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class CreateReviewRequest {

    private int rating;
    private String comment;
    private Long userId;
    private Long productId;
}
