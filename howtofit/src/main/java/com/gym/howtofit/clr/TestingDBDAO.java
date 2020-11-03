package com.gym.howtofit.clr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gym.howtofit.beans.Company;
import com.gym.howtofit.beans.Gym;
import com.gym.howtofit.beans.User;
import com.gym.howtofit.dbdao.CompanyDBDAO;
import com.gym.howtofit.dbdao.UserDBDAO;

@Component
@Order(1)
public class TestingDBDAO implements CommandLineRunner {
	@Autowired
	private UserDBDAO userDBDAO;
	@Autowired
	private CompanyDBDAO companyDBDAO;

	@Override
	public void run(String... args) throws Exception {

		// Create Users Data for Testing
		createUsers();

		// Create Companies And Gyms Data for Testing
		createCompaniesAndGyms();
	}

	public void createUsers() {
		User user = User.builder().first("elad").last("hakmon").email("elad@gmail.com").password("elad1234").build();
		userDBDAO.addUser(user);
		for (int i = 0; i < 20; i++) {
			userDBDAO.addUser(generateUser());
			System.out.println("new Customer as added :-)");

		}

		System.out.println("all Customers as added Successfully!");
		space();
	}

	public User generateUser() {
		User user = User.builder().first(generateFirstName()).last(generateLastName()).build();
		user.setEmail(generateEmail(user));
		user.setPassword(generatePassword(user));
		return user;
	}

	public String generateFirstName() {
		int rand = (int) (Math.random() * 25);
		switch (rand) {
		case 0:
			return "elad";
		case 1:
			return "kobi";
		case 2:
			return "dan";
		case 3:
			return "haki";
		case 4:
			return "heli";
		case 5:
			return "natali";
		case 6:
			return "omer";
		case 7:
			return "karin";
		case 8:
			return "danit";
		case 9:
			return "yossi";
		case 10:
			return "hen";
		case 11:
			return "ben";
		case 12:
			return "yoni";
		case 13:
			return "kfir";
		case 14:
			return "sharon";
		case 15:
			return "yaeli";
		case 16:
			return "dana";
		case 17:
			return "sapir";
		case 18:
			return "moshe";
		case 19:
			return "rotem";
		case 20:
			return "eden";
		case 21:
			return "israel";
		case 22:
			return "noha";
		case 23:
			return "malachi";
		case 24:
			return "tchahi";

		default:
			return null;
		}
	}

	public String generateLastName() {
		String string = " ";
		Random random = new Random();
		int r = (int) (Math.random() * 8) + 2;
		String abc = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < r; i++) {
			string += abc.charAt(random.nextInt(abc.length()));
		}
		return string;
	}

	public String generateEmail(User user) {
		return user.getFirst() + (int) (Math.random() * 1000 + 100) + "@gmail.com";
	}

	public String generatePassword(User user) {
		return user.getLast() + "12345";
	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}

	public String generateCityForGym() {
		int rand = (int) (Math.random() * 25);
		switch (rand) {
		case 0:
			return "Bat-Yam";
		case 1:
			return "Holon";
		case 2:
			return "Tel-Aviv";
		case 3:
			return "Rishon-Lezion";
		case 4:
			return "Afula";
		case 5:
			return "Arad";
		case 6:
			return "Ashdod";
		case 7:
			return "Ashkelon";
		case 8:
			return "Bnei Brak";
		case 9:
			return "Dimona";
		case 10:
			return "Eilat";
		case 11:
			return "El'ad";
		case 12:
			return "Givatayim";
		case 13:
			return "Haifa";
		case 14:
			return "Herzliya";
		case 15:
			return "Hod HaSharon";
		case 16:
			return "Jerusalem";
		case 17:
			return "Kfar Saba";
		case 18:
			return "Kiryat Gat";
		case 19:
			return "Lod";
		case 20:
			return "Nahariya";
		case 21:
			return "Ness Ziona";
		case 22:
			return "Netanya";
		case 23:
			return "Or Yehuda";
		case 24:
			return "Ramat Gan";

		default:
			return null;
		}
	}

	public Gym generateGym(int companyID) {
		Gym gym = Gym.builder().city(generateCityForGym()).companyID(companyID).build();

		return gym;
	}

	public List<Gym> generateGymsListForCompany(int companyID) {
		List<Gym> gyms = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			gyms.add(generateGym(companyID));
		}
		return gyms;
	}

	public void createCompaniesAndGyms() {
		Company c1 = Company.builder().name("Profit").email("profit@gmail.com").password("profit1234").build();
		c1.setGyms(generateGymsListForCompany(1));

		Company c2 = Company.builder().name("Vibe").email("vibe@gmail.com").password("vibe1234").build();
		c2.setGyms(generateGymsListForCompany(2));

		Company c3 = Company.builder().name("Go Active").email("goactive@gmail.com").password("goactive1234").build();
		c3.setGyms(generateGymsListForCompany(3));

		Company c4 = Company.builder().name("Kantree").email("kantree@gmail.com").password("kantree1234").build();
		c4.setGyms(generateGymsListForCompany(4));

		Company c5 = Company.builder().name("Safari").email("safari@gmail.com").password("safari1234").build();
		c5.setGyms(generateGymsListForCompany(5));

		companyDBDAO.addCompany(c1);
		checkIfCompanyAdded(c1.getName());

		companyDBDAO.addCompany(c2);
		checkIfCompanyAdded(c2.getName());

		companyDBDAO.addCompany(c3);
		checkIfCompanyAdded(c3.getName());

		companyDBDAO.addCompany(c4);
		checkIfCompanyAdded(c4.getName());

		companyDBDAO.addCompany(c5);
		checkIfCompanyAdded(c5.getName());
		space();
		space();
		space();

	}

	public void checkIfCompanyAdded(String name) {
		System.out.println("the Company:" + name + " as added Successfully!");
	}

}
