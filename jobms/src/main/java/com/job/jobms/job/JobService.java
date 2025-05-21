package com.job.jobms.job;

import java.util.List;

import com.job.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {
	
	List<JobWithCompanyDTO> findAll();
	
	void createJob(Job job);

	Job getJobById(Long id);

	boolean deleteJobById(Long id);

	boolean updateJob(Long id, Job updatedJob);

}
