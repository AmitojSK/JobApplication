package com.job.jobms.job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.job.jobms.job.external.Review;

@FeignClient(name = "REVIEWYMS")
public interface ReviewClient {
	
	@GetMapping(name = "/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long id);

}
