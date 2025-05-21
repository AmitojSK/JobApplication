package com.job.companyms.Company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Company>> findAll(){
		return ResponseEntity.ok(companyService.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Company> findById(@PathVariable Long id){
		Company company = companyService.findById(id);
		
		if(company!=null) {
			return new  ResponseEntity<>(company, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		companyService.addCompany(company);
		
		return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, 
		@RequestBody Company updatedCompany){
		
		boolean updated = companyService.updateCompany(updatedCompany, id);
		
		if(updated) {
			return new ResponseEntity<>("Company updated successfully.",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
		
		boolean deleted = companyService.deleteCompanyById(id);
		
		if(deleted) {
			return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
