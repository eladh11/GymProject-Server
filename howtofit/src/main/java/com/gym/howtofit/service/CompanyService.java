package com.gym.howtofit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.beans.Gym;
import com.gym.howtofit.exception.IncorrectDetailsException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID;

	@Override
	public boolean login(String email, String password) {

		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company comp : companies) {
			if (comp.getEmail().equalsIgnoreCase(email) && comp.getPassword().equals(password)) {
				System.out.println("Company login Successfully!");
				this.companyID = comp.getId();
				return true;
			}
		}
		System.out.println("Wrong Details...");
		return false;
	}

	public boolean isGymExist(int gymID) {
		return gymDBDAO.isGymExist(gymID);
	}

	public void addGym(Gym gym) {
		gymDBDAO.addGym(gym);
		System.out.println("The Gym as added!");
	}

	public void updateGym(Gym gym) {
		gymDBDAO.updateGym(gym);
		System.out.println("The Gym updated!");

	}

	public void deleteGym(Gym gym) throws IncorrectDetailsException {
		try {
			gymDBDAO.deleteGym(gym);
			System.out.println("The Gym Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("There was a Problem when try Delete Gym...");
		}
	}

	public void deleteGymByID(int gymID) throws IncorrectDetailsException {
		try {
			gymDBDAO.deleteGymByID(gymID);
			;
			System.out.println("The Gym Deleted Successfully!");
		} catch (Exception e) {
			throw new IncorrectDetailsException("The Gym With ID:" + gymID + " was not Found...");
		}
	}

	public List<Gym> getAllGyms() {
		return gymDBDAO.getAllGyms();
	}

	public Gym getOneGym(int gymID) throws IncorrectDetailsException {
		try {
			return gymDBDAO.getOneGym(gymID).get();
		} catch (Exception e) {
			throw new IncorrectDetailsException("The Gym With ID:" + gymID + " was not Found...");
		}
	}

	public List<Gym> getCompanyGyms(int companyID) {
		List<Gym> idx = new ArrayList<Gym>();
		List<Gym> gyms = gymDBDAO.getAllGyms();
		for (Gym g : gyms) {
			if (g.getCompanyID() == companyID) {
				idx.add(g);
			}
		}
		return idx;
	}

}
