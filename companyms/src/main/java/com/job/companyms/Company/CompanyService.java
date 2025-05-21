package com.job.companyms.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;
	
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
	

}
