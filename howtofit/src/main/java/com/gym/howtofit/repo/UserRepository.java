package com.gym.howtofit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.howtofit.beans.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
