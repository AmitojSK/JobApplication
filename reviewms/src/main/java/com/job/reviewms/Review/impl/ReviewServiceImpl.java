package com.job.reviewms.Review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job.reviewms.Review.Review;
import com.job.reviewms.Review.ReviewRepository;
import com.job.reviewms.Review.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}


	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		
		return reviews;
	}


	@Override
	public boolean addReview(Long companyId, Review review) {
		if(companyId!=null && review!=null) {
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			return true;
		}
		
		return false;
		
	}


	@Override
	public Review getReview(Long reviewId) {
		return reviewRepository.findById(reviewId).orElse(null);
	}


	@Override
	public boolean updateReview(Long reviewId, Review updatedReview) {

		Review review = reviewRepository.findById(reviewId).orElse(null);
		if(review!=null) {
			review.setCompanyId(updatedReview.getCompanyId());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setTitle(updatedReview.getTitle());
			reviewRepository.save(review);
			return true;
		}
		
		return false;
		
	}


	@Override
	public boolean deleteReview(Long reviewId) {

		Review review = reviewRepository.findById(reviewId).orElse(null);	
		if(review!=null) {
			
			reviewRepository.delete(review);
			return true;
		}
		
		return false;
	}
	

}
