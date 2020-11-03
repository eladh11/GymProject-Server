package com.gym.howtofit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.beans.User;
import com.gym.howtofit.exception.IncorrectDetailsException;

@Service
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (email.equalsIgnoreCase("eladhakmon11@gmail.com") && password.equals("e1l1a1d1")) {
			System.out.println("Admin login Successfully!");
			return true;
		}
		System.out.println("The Details are Incorrect...");
		return false;
	}

	public boolean isUserExist(int userID) {
		return userDBDAO.isUserExist(userID);
	}

	public void addUser(User user) throws IncorrectDetailsException {

		List<User> users = userDBDAO.getAllUsers();
		for (User u : users) {
			if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
				throw new IncorrectDetailsException("the Email are all ready Exist...");
			}
		}
		userDBDAO.addUser(user);
		System.out.println("The User:" + user.getFirst() + " added Successfully!");

	}

	public void updateUser(User user) throws IncorrectDetailsException {
	 
		userDBDAO.updateUser(user);
		System.out.println("The User:" + user.getFirst() + " Updated!");

	}

	public void DeleteUser(User user) throws IncorrectDetailsException {
		try {
			userDBDAO.deleteUser(user);
			System.out.println("The User:" + user.getFirst() + " Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("There was a Problem try Delete User:" + user.getFirst());
		}

	}

	public void DeleteUserByID(int userID) throws IncorrectDetailsException {
		try {
			userDBDAO.deleteUserByID(userID);
			System.out.println("The User Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("The User with ID:" + userID + " Was not found...");
		}
	}

	public User GetOneUser(int userID) throws IncorrectDetailsException {

		try {
			return userDBDAO.getOneUser(userID).get();
		} catch (Exception e) {
			throw new IncorrectDetailsException("The User with ID:" + userID + " Was not found...");
		}
	}

	public List<User> getAllUsers() {
		return userDBDAO.getAllUsers();
	}

	public User GetOneUserByEmail(String email) throws IncorrectDetailsException {
		try {
			return userDBDAO.getUserByEmail(email);
		} catch (Exception e) {
			throw new IncorrectDetailsException("The User with Email:" + email + " Was not found...");
		}
	}

	public boolean isCompanyExist(int companyID) {
		return companyDBDAO.isCompanyExist(companyID);
	}

	public void addCompany(Company company) throws IncorrectDetailsException {

		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company comp : companies) {
			if (company.getName().equalsIgnoreCase(comp.getName())) {
				throw new IncorrectDetailsException("Company With Name:" + company.getName() + " already Exist...");
			}
			if (company.getEmail().equalsIgnoreCase(comp.getEmail())) {
				throw new IncorrectDetailsException("Company With Email:" + company.getEmail() + " already Exist...");
			}
		}

		companyDBDAO.addCompany(company);
		System.out.println("The Company:" + company.getName() + " added Successfully!");

	}

	public void updateCompany(Company company) throws IncorrectDetailsException {
	 
		companyDBDAO.updateCompany(company);
		System.out.println("The Company:" + company.getName() + " updated!");

	}

	public void DeleteCompany(Company company) throws IncorrectDetailsException {
		try {
			companyDBDAO.deleteCompany(company);
			System.out.println("The Company:" + company.getName() + " Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("There was a Problem try Delete Company:" + company.getName());
		}
	}

	public void DeleteCompanyByID(int companyID) throws IncorrectDetailsException {
		try {
			companyDBDAO.deleteCompanyByID(companyID);
			System.out.println("The Company Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("The Company with ID:" +companyID + " Was not found...");
		}
	}

	public Company GetOneCompany(int companyID) throws IncorrectDetailsException {

		try {
			return companyDBDAO.getOneCompany(companyID).get();
		} catch (Exception e) {
			throw new IncorrectDetailsException("The Company with ID:" + companyID + " Was not found...");
		}
	}

	public List<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public Company GetOneCompanyByEmail(String email) throws IncorrectDetailsException {
		try {
			return companyDBDAO.getCompanyByEmail(email);
		} catch (Exception e) {
			throw new IncorrectDetailsException("The Company with Email:" + email + " Was not found...");
		}
	}

}
