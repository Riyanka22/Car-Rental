package com.myProject.car_rent.service;

import java.util.List;

import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;

public interface AdminService {
	
	//view all cars
	public List<Car> showAllCars();
	//add new car
	public Car addCar(Car car) throws CarAreadyPresentException,NullValueEnteredException,InvalidDataFormatException;
	//remove car
	public String deleteCar(int carId) throws CarNotFoundException;
	//edit car
	public Car editCar(Car car) throws CarNotFoundException;
	
	//see all users
	public List<User> showAllUsers();
	
	// show all bookings
	public List<Booking> showAllBookings();
	public Admin getAdmin(String email) throws AdminNotFoundException;
	public Car getCar(Integer carId);
}
