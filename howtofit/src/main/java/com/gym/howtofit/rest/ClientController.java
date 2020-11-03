package com.gym.howtofit.rest;

import org.springframework.http.ResponseEntity;

public abstract class ClientController {

	public abstract ResponseEntity<?> login(String email, String password);

}
