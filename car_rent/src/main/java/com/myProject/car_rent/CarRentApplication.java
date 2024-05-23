package com.myProject.car_rent;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CarRentApplication implements CommandLineRunner  {

	
	public static void main(String[] args) {
		SpringApplication.run(CarRentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("~~.~~ APPLICATION STARTED SUCCESSFULLY ~~.~~");
		
	}

}
