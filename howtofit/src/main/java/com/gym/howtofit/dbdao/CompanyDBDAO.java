package com.gym.howtofit.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.repo.CompanyRepository;

@Repository
public class CompanyDBDAO {

	@Autowired
	private CompanyRepository repo;

	public boolean isCompanyExist(int companyID) {
		return repo.existsById(companyID);
	}

	public void addCompany(Company company) {
		repo.save(company);
	}

	public void updateCompany(Company company) {
		repo.saveAndFlush(company);
	}

	public void deleteCompany(Company company) {
		repo.delete(company);
	}

	public void deleteCompanyByID(int companyID) {
		repo.deleteById(companyID);
	}

	public List<Company> getAllCompanies() {
		return repo.findAll();
	}

	public Optional<Company> getOneCompany(int companyID) {
		return repo.findById(companyID);
	}

	public Company getCompanyByEmail(String email) {
		return repo.findByEmail(email);
	}

}
