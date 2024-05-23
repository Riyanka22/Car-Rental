package com.myProject.car_rent.service;

import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.util.Login;

public interface LoginService {
	
	//user login
	public User loginUser(Login login)throws UserNotFoundException;
	
	//admin login
	public Admin loginAdmin(Login login)throws AdminNotFoundException;
	
}
