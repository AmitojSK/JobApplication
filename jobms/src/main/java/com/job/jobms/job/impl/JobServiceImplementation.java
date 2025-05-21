package com.job.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.job.jobms.job.Job;
import com.job.jobms.job.JobRepository;
import com.job.jobms.job.JobService;
import com.job.jobms.job.dto.JobWithCompanyDTO;
import com.job.jobms.job.external.Company;


@Service
public class JobServiceImplementation implements JobService{


	private JobRepository jobRepository;

	public JobServiceImplementation(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobWithCompanyDTO> findAll() {

		List<Job> jobs = jobRepository.findAll();

		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private JobWithCompanyDTO convertToDto(Job job) {
		RestTemplate restTemplate = new RestTemplate();
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		Company company = restTemplate.getForObject("http://localhost:8081/companies/"+job.getCompanyId(), Company.class);
		jobWithCompanyDTO.setCompany(company);
		jobWithCompanyDTO.setJob(job);

		return jobWithCompanyDTO;
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);

	}

	@Override
	public Job getJobById(Long id) {

		return jobRepository.findById(id).orElse(null);

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
