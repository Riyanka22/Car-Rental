package com.myProject.car_rent.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "first_name" , nullable=false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number", nullable=false)
	private String phoneNumber;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth ;
	
	@Column(name = "driving_license", nullable=false)
	private String drivingLicense;

	@Column(name = "password" , nullable=false)
	private String password ;
	
	@Column(name = "registration_date")
	private LocalDate registrationDate;

	
	//constructor
	public User(String userEmail, String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth,
			String drivingLicense, String password) {
		super();
		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.drivingLicense = drivingLicense;
		this.password = password;
		
	}
	
}
