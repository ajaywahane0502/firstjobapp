package com.jobapp.firstjobapp.review.Impl;

import com.jobapp.firstjobapp.Company.Company;
import com.jobapp.firstjobapp.Company.CompanyService;
import com.jobapp.firstjobapp.review.Review;
import com.jobapp.firstjobapp.review.ReviewRepository;
import com.jobapp.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
 private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        List<Review> reviews=reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getcompanybyId(companyId);

        if(company !=null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return  true;
        }else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getcompanybyId(companyId) !=null)
        {
            updatedReview.setCompany(companyService.getcompanybyId(companyId));
            updatedReview.setId(reviewId);

            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }
}