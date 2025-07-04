package com.job.companyms.Company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "REVIEWMS", 
		url = "${review-service.url}")
public interface ReviewClient {
	
	@GetMapping("/reviews/averageRating")
	Double getAverageRatingForCompany(@RequestParam("companyId") Long id);

}