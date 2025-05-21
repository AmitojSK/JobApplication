package com.job.jobms.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.jobms.job.dto.JobWithCompanyDTO;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	private JobService jobservice;
	
	public JobController(JobService jobservice) {
		this.jobservice = jobservice;
	}

	@GetMapping
	public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
		return ResponseEntity.ok(jobservice.findAll());
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobservice.createJob(job);
		
		return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		
		Job job = jobservice.getJobById(id);
		
		if(job != null) {
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id){
		
		boolean deleted = jobservice.deleteJobById(id);
		
		if(deleted) {
			return new ResponseEntity<>("Job deleted succesfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id,
			@RequestBody Job updatedJob){
		
		boolean updated = jobservice.updateJob(id, updatedJob);
		
		if(updated){
			return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
