package com.gym.howtofit.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.howtofit.beans.Gym;
import com.gym.howtofit.repo.GymRepository;

@Repository
public class GymDBDAO {

	@Autowired
	private GymRepository repo;

	public boolean isGymExist(int gymID) {
		return repo.existsById(gymID);
	}

	public void addGym(Gym gym) {
		repo.save(gym);
	}

	public void updateGym(Gym gym) {
		repo.saveAndFlush(gym);
	}

	public void deleteGym(Gym gym) {
		repo.delete(gym);
	}

	public void deleteGymByID(int gymID) {
		repo.deleteById(gymID);
	}

	public List<Gym> getAllGyms() {
		return repo.findAll();
	}

	public Optional<Gym> getOneGym(int gymID) {
		return repo.findById(gymID);
	}

}
