package com.myProject.car_rent.service;

import java.util.List;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserAreadyPresentException;
import com.myProject.car_rent.exception.UserNotFoundException;

public interface UserService {
	
	//user registration
	public User registration(User user) throws UserAreadyPresentException,InvalidDataFormatException,NullValueEnteredException;
	
	//view All available cars in particular city
	public List<Car> showAllCars(String city);
	
	//view booking details of particular user
	public List<Booking> viewBookingDetails(String userEmail) throws UserNotFoundException;
	
	//book a car
	public Booking bookCar(Booking booking) throws BookingNotAllowedException;
	
	//search cars by city
	public List<Car> searchByCity(String city);
	
	//search cars by seater
	public List<Car> searchBySeater(String city,int seater);
	
	//search cars by brand
	public List<Car> searchByBrand(String city,String brand);
	
	//search cars by segment
	public List<Car> searchBySegment(String city,String segment);
		
	//search cars by fuel
	public List<Car> searchByFuel(String city,String fuel);
	
	public User getUser(String email) throws UserNotFoundException;
	
	//for admin
	public List<User> showAllUsers();
	
	
	//sort cars by price
	public Car getCar(Integer carId);
	
}
