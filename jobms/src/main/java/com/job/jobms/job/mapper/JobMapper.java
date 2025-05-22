package com.job.jobms.job.mapper;

import java.util.List;

import com.job.jobms.job.Job;
import com.job.jobms.job.dto.JobDTO;
import com.job.jobms.job.external.Company;
import com.job.jobms.job.external.Review;

public class JobMapper {
	
	public static JobDTO mapToJobDto(Job job, Company company, List<Review> reviews) {
		JobDTO jobWithCompanyDto = new JobDTO();
		jobWithCompanyDto.setId(job.getId());
		jobWithCompanyDto.setDescription(job.getDescription());
		jobWithCompanyDto.setCompany(company);
		jobWithCompanyDto.setLocation(job.getLocation());
		jobWithCompanyDto.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDto.setMinSalary(job.getMinSalary());
		jobWithCompanyDto.setTitle(job.getTitle());
		jobWithCompanyDto.setReviews(reviews);
		
		return jobWithCompanyDto;
		
	}

}
