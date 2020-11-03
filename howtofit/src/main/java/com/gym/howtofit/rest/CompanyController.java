package com.gym.howtofit.rest;

import java.util.ArrayList;
import java.util.List;

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
import com.gym.howtofit.beans.Gym;
import com.gym.howtofit.beans.LoginResponse;
import com.gym.howtofit.exception.IncorrectDetailsException;
import com.gym.howtofit.security.ClientType;
import com.gym.howtofit.security.LoginManager;
import com.gym.howtofit.service.AdminService;
import com.gym.howtofit.service.CompanyService;

@RestController
@RequestMapping("company")
@CrossOrigin(value = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController extends ClientController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginManager loginManager;

	public CompanyController() {
		super();
	}

	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		String token = loginManager.loginToken(email, password, ClientType.Company);
		List<Company> companies = adminService.getAllCompanies();
		for (Company company : companies) {
			if (company.getPassword().equals(password) && company.getEmail().equalsIgnoreCase(email) && token != null) {
				System.out.println("Company login Successfully!");
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setType("company");
				return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.CREATED);

			}
		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("add-gym")
	public ResponseEntity<?> addGym(@RequestBody Gym gym) {
		companyService.addGym(gym);
		return new ResponseEntity<Gym>(HttpStatus.CREATED);
	}

	@PutMapping("update-gym")
	public ResponseEntity<?> updateGym(@RequestBody Gym gym) {
		companyService.updateGym(gym);
		return ResponseEntity.ok(gym);
	}

	@DeleteMapping("delete-gym/{gymID}")
	public ResponseEntity<?> deleteGym(@PathVariable(name = "gymID") int gymID) throws IncorrectDetailsException {
		companyService.deleteGymByID(gymID);
		return new ResponseEntity<Gym>(HttpStatus.OK);
	}

	@GetMapping("get-all-company-gyms/{id}")
	public ResponseEntity<?> getAllCompanyGyms(@PathVariable(name = "id") int id) {
		return new ResponseEntity<ArrayList<Gym>>((ArrayList<Gym>) companyService.getCompanyGyms(id), HttpStatus.OK);
	}

	@GetMapping("get-all-gyms")
	public ResponseEntity<?> getAllGyms() {
		return new ResponseEntity<ArrayList<Gym>>((ArrayList<Gym>) companyService.getAllGyms(), HttpStatus.OK);
	}

}
