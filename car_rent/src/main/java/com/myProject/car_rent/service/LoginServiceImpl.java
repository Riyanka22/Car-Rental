package com.myProject.car_rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.repository.AdminRepository;
import com.myProject.car_rent.repository.UserRepository;
import com.myProject.car_rent.util.Login;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	final private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	@Override
	//login for user
	public User loginUser(Login login) throws UserNotFoundException
	{
		log.info("Login user : {}",login);
		String email=login.getEmail();
		User user=userRepository.findById(email).orElse(null);
		if(user==null)
		{
			throw new UserNotFoundException("User NOT found !!");
		}
		
		String rawPassword=login.getPassword();
		String actualPassword=user.getPassword();
		
		if(passwordEncoder.matches(rawPassword,actualPassword))
			return user;
		else 
			throw new UserNotFoundException("Password doesn't match !!");
	}
	
	@Override
	//login for admin
	public Admin loginAdmin(Login login) throws AdminNotFoundException
	{
		log.info("Login Admin : {}",login);
		String email=login.getEmail();
		Admin admin=adminRepository.findById(email).orElse(null);
		if(admin==null)
		{
			throw new AdminNotFoundException("Admin NOT found !!");
		}
		String password=login.getPassword();
		if(password.equals(admin.getPassword()))
			return admin;
		else 
			throw new AdminNotFoundException("Password doesn't match !!");
	}
}
