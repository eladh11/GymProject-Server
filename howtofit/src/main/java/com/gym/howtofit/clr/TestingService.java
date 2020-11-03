package com.gym.howtofit.clr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.beans.Gym;
import com.gym.howtofit.beans.User;
import com.gym.howtofit.exception.IncorrectDetailsException;
import com.gym.howtofit.security.ClientType;
import com.gym.howtofit.security.LoginManager;
import com.gym.howtofit.service.AdminService;
import com.gym.howtofit.service.CompanyService;
import com.gym.howtofit.service.UserService;

@Component
@Order(2)
public class TestingService implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;

	@Override
	public void run(String... args) throws Exception {
		// Test All Services
		testService();
	}

	public void testService() throws IncorrectDetailsException {
		AdminService admin = (AdminService) loginManager.login("eladhakmon11@gmail.com", "e1l1a1d1",
				ClientType.Administrator);

		Company company = Company.builder().name("Service Gym").email("servicegym@gamil.com").password("stam1234")
				.build();

		admin.addCompany(company);
		System.out.println(admin.GetOneCompany(company.getId()));
		space();
		company.setName("Service");
		admin.updateCompany(company);
		System.out.println(admin.getAllCompanies());
		space();

		CompanyService companyService = (CompanyService) loginManager.login("profit@gmail.com", "profit1234",
				ClientType.Company);

		Gym gym = Gym.builder().companyID(3).city("Holon").build();
		company.setGyms(Arrays.asList(gym));
		admin.updateCompany(company);

		System.out.println(companyService.getCompanyGyms(3));
		space();
		gym.setCity("Bat-Yam");
		companyService.updateGym(gym);
		System.out.println(companyService.getCompanyGyms(3));
		space();
		companyService.deleteGym(gym);
		System.out.println(companyService.getCompanyGyms(3));
		space();

		User user = User.builder().first("e").last("h").email("e@gmail.com").password("e1234").build();
		admin.addUser(user);
		space();
		UserService userService = (UserService) loginManager.login("e@gmail.com", "e1234", ClientType.User);
		space();
		System.out.println(userService.getUserDetails("e@gmail.com"));
		space();
		space();

	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}
}
