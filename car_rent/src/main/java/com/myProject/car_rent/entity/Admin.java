package com.myProject.car_rent.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="admins")
public class Admin {
	
	@Id
	private String email;
	
	@Column(name = "first_name", nullable=false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number" , nullable=false)
	private String phoneNumber;
	
	@Column(name = "password" , nullable=false)
	private String password;

}
