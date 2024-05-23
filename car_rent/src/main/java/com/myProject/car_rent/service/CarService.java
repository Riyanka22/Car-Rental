package com.myProject.car_rent.service;

import java.util.List;

import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;

public interface CarService {
							// Admin
	//show car
	public List<Car> showAllCarsForAdmin();
	//add car
	public Car addCar(Car car) throws CarAreadyPresentException,NullValueEnteredException,InvalidDataFormatException;
	//remove car
	public String deleteCar(int car_id) throws CarNotFoundException;
	//edit car
	public Car editCar(Car car) throws CarNotFoundException;
	
							// User
	//view all cars based on city & availability status
	public List<Car> showAllCars(String city);
	//search by city
	public List<Car> searchByCity(String city);
	//search by seat
	public List<Car> searchBySeater(String city,int seater);
	//search by brand
	public List<Car> searchByBrand(String city,String brand);
	//search by segment
	public List<Car> searchBySegment(String city, String segment);
	//search by fuel
	public List<Car> searchByFuel(String city,String fuel);
	
	public Car getCar(Integer carId);
	
	
}
