package com.gym.howtofit.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gym.howtofit.beans.User;
import com.gym.howtofit.exception.IncorrectDetailsException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class UserService extends ClientService {

	private int userID;

	@Override
	public boolean login(String email, String password) {

		List<User> users = userDBDAO.getAllUsers();
		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				System.out.println("The User login Successfully!");
				this.userID = user.getId();
				return true;
			}
		}

		System.out.println("Wrong Details...");
		return false;
	}

	public User getUserDetails(int userID) throws IncorrectDetailsException {
		try {
			return userDBDAO.getOneUser(userID).get();
		} catch (Exception e) {
			throw new IncorrectDetailsException("The User With ID:" + userID + " was not Found...");
		}
	}

	public User getUserDetails(String email) throws IncorrectDetailsException {
		try {
			return userDBDAO.getUserByEmail(email);
		} catch (Exception e) {
			throw new IncorrectDetailsException("The User With email:" + email + " was not Found...");
		}
	}

}
