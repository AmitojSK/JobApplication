package com.job.companyms.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.companyms.Company.clients.ReviewClient;
import com.job.companyms.Company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;


@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;
	
	@Autowired
	private ReviewClient reviewClient;
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public void addCompany(Company company) {
		companyRepository.save(company);
		
	}
	
	public boolean updateCompany(Company updatedCompany, Long id) {
		Optional<Company> compnayOptional = companyRepository.findById(id);

		if(compnayOptional.isPresent()) {
			Company company = compnayOptional.get();
			company.setName(updatedCompany.getName());
			companyRepository.save(company);
			return true;
		}

		return false;
	}

	public Company findById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}
	
	public boolean deleteCompanyById(Long id) {
		
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		
		return false;
		
	}

	public void updateCompanyRating(ReviewMessage reviewMessage) {
		System.out.println("updateCompanyRating: "+reviewMessage.getDescription());
		Company company = companyRepository.findById(reviewMessage.getCompanyId())
				.orElseThrow(()-> new NotFoundException("Company not found "+reviewMessage.getCompanyId()));

		Double avgRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(avgRating);
		companyRepository.save(company);
		
	}
	

}
