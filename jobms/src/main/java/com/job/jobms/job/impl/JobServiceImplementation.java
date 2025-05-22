package com.job.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.job.jobms.job.Job;
import com.job.jobms.job.JobRepository;
import com.job.jobms.job.JobService;
import com.job.jobms.job.dto.JobDTO;
import com.job.jobms.job.external.Company;
import com.job.jobms.job.external.Review;
import com.job.jobms.job.mapper.JobMapper;


@Service
public class JobServiceImplementation implements JobService{


	private JobRepository jobRepository;
	@Autowired
	private RestTemplate restTemplate;

	public JobServiceImplementation(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobDTO> findAll() {

		List<Job> jobs = jobRepository.findAll();

		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private JobDTO convertToDto(Job job) {
		Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/"+job.getCompanyId(), Company.class);
		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId="+job.getCompanyId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Review>>	() {
			
		});
			return JobMapper.mapToJobDto(job, company, reviewResponse.getBody());
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);

	}

	@Override
	public JobDTO getJobById(Long id) {

		return convertToDto(jobRepository.findById(id).orElse(null));

	}

	@Override
	public boolean deleteJobById(Long id) {

		try {
			jobRepository.deleteById(id);
		} catch(Exception e) {
			return false;
		}

		return true;

	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);

		if(jobOptional.isPresent()) {
			Job job = jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepository.save(job);
			return true;
		}

		return false;
	}



}
