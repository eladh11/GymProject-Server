package com.gym.howtofit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.howtofit.dbdao.CompanyDBDAO;
import com.gym.howtofit.dbdao.GymDBDAO;
import com.gym.howtofit.dbdao.UserDBDAO;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyDBDAO companyDBDAO;
	@Autowired
	protected GymDBDAO gymDBDAO;
	@Autowired
	protected UserDBDAO userDBDAO;

	public abstract boolean login(String email, String password);
}
