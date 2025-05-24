package com.job.jobms.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.job.jobms.job.external.Company;

@FeignClient(name = "COMPANYMS", 
	url = "${company-service.url}")
public interface CompanyClient {

	@GetMapping("/companies/{id}")
	Company getCompany(@PathVariable("id") Long id);

}
