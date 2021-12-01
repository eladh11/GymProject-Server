package com.gym.howtofit.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.beans.LoginResponse;
import com.gym.howtofit.beans.User;
import com.gym.howtofit.exception.IncorrectDetailsException;
import com.gym.howtofit.security.ClientType;
import com.gym.howtofit.security.LoginManager;
import com.gym.howtofit.service.AdminService;
import com.gym.howtofit.utils.Env;
 
@RestController
@RequestMapping("admin")
@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
public class AdminController extends ClientController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private AdminService adminService;

	public AdminController() {
		super();
	}

	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

		String token = loginManager.loginToken(email, password, ClientType.Administrator);
		if (password.equals("e1l1a1d1") && email.equalsIgnoreCase("eladhakmon11@gmail.com") && token != null) {
			System.out.println("Admin login Successfully!");
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(token);
			loginResponse.setType("admin");
			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.CREATED);

		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);

	}

	@PostMapping("add-company")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws IncorrectDetailsException {
		adminService.addCompany(company);
		return new ResponseEntity<Company>(HttpStatus.CREATED);
	}

	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws IncorrectDetailsException {
		adminService.updateCompany(company);
		return ResponseEntity.ok(company);
	}

	@DeleteMapping("delete-company/{companyID}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "companyID") int companyID)
			throws IncorrectDetailsException {
		adminService.DeleteCompanyByID(companyID);
		return new ResponseEntity<Company>(HttpStatus.OK);
	}

	@GetMapping("get-all-companies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<ArrayList<Company>>((ArrayList<Company>) adminService.getAllCompanies(),
				HttpStatus.OK);

	}

	@GetMapping("get-one-company/{companyID}")
	public ResponseEntity<?> getOneCompany(@PathVariable(name = "companyID") int companyID)
			throws IncorrectDetailsException {
		return new ResponseEntity<Company>(adminService.GetOneCompany(companyID), HttpStatus.OK);
	}

	@GetMapping("get-one-company-email/{email}")
	public ResponseEntity<?> getOneCompanyByEmail(@PathVariable(name = "email") String email)
			throws IncorrectDetailsException {
		return new ResponseEntity<Company>(adminService.GetOneCompanyByEmail(email), HttpStatus.OK);
	}

	@PostMapping("add-user")
	public ResponseEntity<?> addUser(@RequestBody User user) throws IncorrectDetailsException {
		adminService.addUser(user);
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}

	@PutMapping("update-user")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws IncorrectDetailsException {
		adminService.updateUser(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("delete-user/{userID}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "userID") int userID) throws IncorrectDetailsException {
		adminService.DeleteUserByID(userID);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@GetMapping("/get-all-users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<ArrayList<User>>((ArrayList<User>) adminService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("get-one-user/{userID}")
	public ResponseEntity<?> getOneUser(@PathVariable int userID) throws IncorrectDetailsException {
		User user = adminService.GetOneUser(userID);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("get-one-user-email/{email}")
	public ResponseEntity<?> getOneUserByEmail(@PathVariable(name = "email") String email)
			throws IncorrectDetailsException {
		return new ResponseEntity<User>(adminService.GetOneUserByEmail(email), HttpStatus.OK);
	}

}
