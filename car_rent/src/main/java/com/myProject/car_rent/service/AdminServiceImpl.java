package com.myProject.car_rent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.repository.AdminRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private CarService carService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdminRepository adminRepository;
	                                 // car
	//view all cars
	@Override
	public List<Car> showAllCars(){
		log.info("[AdminService] Showing all cars");
		return carService.showAllCarsForAdmin();
	}
	//add new car
	@Override
	public Car addCar(Car car) throws CarAreadyPresentException,NullValueEnteredException,InvalidDataFormatException {
		log.info("[AdminService] Adding new car: {}",car);
		return carService.addCar(car);
	}
	//remove car
	@Override
	public String deleteCar(int car_id) throws CarNotFoundException{
		log.info("[AdminService] Deleting car , car Id: {}",car_id);
		return carService.deleteCar(car_id);
	}
	//update car
	@Override
	public Car editCar(Car car) throws CarNotFoundException{
		log.info("[AdminService] editing car details: {}",car);
		return carService.editCar(car);
	}
									 // user
	//see all users
	public List<User> showAllUsers()
	{
		log.info("[AdminService] Showing all users");
		return userService.showAllUsers();
	}
	
								  //Booking
	//show all bookings
	@Override
	public List<Booking> showAllBookings()
	{
		log.info("[AdminService] Showing all Booking details");
		return bookingService.showAllBookings();
	}
	
	
	public Admin getAdmin(String email) throws AdminNotFoundException
	{
		Admin admin=adminRepository.findById(email).orElse(null);
		if(admin==null)
			throw new AdminNotFoundException("Admin does not exist !");
		return admin;
	}
	
	public Car getCar(Integer carId) {
		return carService.getCar(carId);
	}
	
}
