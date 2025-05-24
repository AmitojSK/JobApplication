package com.job.reviewms.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.job.reviewms.Review.Review;
import com.job.reviewms.dto.ReviewMessage;

@Service
public class ReviewMessageProducer {

	private final RabbitTemplate rabbitTemplate;

	public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Review review) {
		ReviewMessage reviewMessage = new ReviewMessage();
		reviewMessage.setCompanyId(review.getCompanyId());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setId(review.getId());
		reviewMessage.setRating(reviewMessage.getRating());
		reviewMessage.setTitle(review.getTitle());
		
		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
	}
	
}
