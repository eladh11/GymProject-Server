package com.gym.howtofit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.howtofit.beans.LoginResponse;
import com.gym.howtofit.beans.User;
import com.gym.howtofit.exception.IncorrectDetailsException;
import com.gym.howtofit.security.ClientType;
import com.gym.howtofit.security.LoginManager;
import com.gym.howtofit.service.AdminService;
import com.gym.howtofit.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(value = "http://localhost:4200", allowedHeaders = "*")
public class UserController extends ClientController {
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginManager loginManager;

	public UserController() {
		super();
	}

	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		String token = loginManager.loginToken(email, password, ClientType.User);
		List<User> users = adminService.getAllUsers();
		for (User us : users) {
			if (us.getPassword().equals(password) && us.getEmail().equalsIgnoreCase(email) && token != null) {
				System.out.println("Customer login Successfully!");
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setType("user");
				return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.CREATED);
			}
		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("get-all-user-details-id/{userID}")
	public ResponseEntity<?> getUserDetailsByID(@PathVariable(name = "userID") int userID)
			throws IncorrectDetailsException {
		return new ResponseEntity<User>(userService.getUserDetails(userID), HttpStatus.OK);
	}

	@GetMapping("get-all-user-details-email/{email}")
	public ResponseEntity<?> getUserDetailsByEmail(@PathVariable(name = "email") String email)
			throws IncorrectDetailsException {
		return new ResponseEntity<User>(userService.getUserDetails(email), HttpStatus.OK);
	}

}
