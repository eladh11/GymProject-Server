package com.gym.howtofit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.howtofit.beans.Gym;

public interface GymRepository extends JpaRepository<Gym, Integer> {

}
