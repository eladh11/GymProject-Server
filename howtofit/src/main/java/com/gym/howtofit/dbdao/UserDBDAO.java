package com.gym.howtofit.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.howtofit.beans.User;
import com.gym.howtofit.repo.UserRepository;

@Repository
public class UserDBDAO {

	@Autowired
	private UserRepository repo;

	public boolean isUserExist(int userID) {
		return repo.existsById(userID);
	}

	public void addUser(User user) {
		repo.save(user);
	}

	public void updateUser(User user) {
		repo.saveAndFlush(user);
	}

	public void deleteUser(User user) {
		repo.delete(user);
	}

	public void deleteUserByID(int userID) {
		repo.deleteById(userID);
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public Optional<User> getOneUser(int userID) {
		return repo.findById(userID);
	}

	public User getUserByEmail(String email) {
		return repo.findByEmail(email);
	}

}
