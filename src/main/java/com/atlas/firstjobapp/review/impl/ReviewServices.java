package com.atlas.firstjobapp.review.impl;

import com.atlas.firstjobapp.review.Review;

import java.util.List;

public interface ReviewServices {
    List<Review> getReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
