package com.gym.howtofit.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.gym.howtofit.service.AdminService;
import com.gym.howtofit.service.ClientService;
import com.gym.howtofit.service.CompanyService;
import com.gym.howtofit.service.UserService;

import lombok.Getter;

@Service
@Lazy
@Getter
public class LoginManager {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;

	public ClientService login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Administrator:
			if (adminService.login(email, password) == true) {
				return adminService;
			}

			System.out.println("Wrong Details...");
			return null;
		case Company:
			if (companyService.login(email, password) == true) {
				return companyService;
			}
			System.out.println("Wrong Details...");
			return null;
		case User:
			if (userService.login(email, password) == true) {
				return userService;
			}
			System.out.println("Wrong Details...");
			return null;

		default:
			System.out.println("the Client type has not found...");
			break;
		}
		return null;
	}

	public String loginToken(String email, String password, ClientType type) {
		return UUID.randomUUID().toString();
	}

}
