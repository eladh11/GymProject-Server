package com.gym.howtofit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.howtofit.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	Company findByEmail(String email);
}
