package com.gym.howtofit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HowtofitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowtofitApplication.class, args);
		System.out.println("IoC Container was loaded!");
	}
}
