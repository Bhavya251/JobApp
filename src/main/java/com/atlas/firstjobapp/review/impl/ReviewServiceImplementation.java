package com.atlas.firstjobapp.review.impl;


import com.atlas.firstjobapp.company.Company;
import com.atlas.firstjobapp.company.impl.CompanyServices;
import com.atlas.firstjobapp.review.Review;
import com.atlas.firstjobapp.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewServices{

    ReviewRepository reviewRepo;
    CompanyServices companyServices;

    public ReviewServiceImplementation(ReviewRepository reviewRepo, CompanyServices companyServices) {
        this.reviewRepo = reviewRepo;
        this.companyServices = companyServices;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyServices.findCompanyID(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = getReviews(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyServices.findCompanyID(companyId) != null){
            updatedReview.setCompany(companyServices.findCompanyID(companyId));
            updatedReview.setId(reviewId);
            reviewRepo.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = getReviewById(companyId, reviewId);
        if (review != null){
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyServices.updateCompany(companyId, company);
            reviewRepo.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
