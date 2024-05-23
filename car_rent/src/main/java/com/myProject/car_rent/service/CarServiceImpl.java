package com.myProject.car_rent.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //type of bean
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
							
							// Admin
	//view all cars
		@Override
		public List<Car> showAllCarsForAdmin(){
			log.info("[CarService] showing all cars for admin");
			List<Car> cars= carRepository.findAll();
			return cars;
		}
		//add new car
		@Override
		public Car addCar(Car car) throws CarAreadyPresentException,NullValueEnteredException,InvalidDataFormatException {
			log.info("[CarService] addCar(): {}",car);
			int carId=car.getCarId();
			// Check if a car with the given ID already exists in the repository
		    if (carRepository.findById(carId).isPresent()) {
		        throw new CarAreadyPresentException("Car already present !!");
		    }  
		    if(car.getCarSegment()==null || car.getCarSegment().trim().isEmpty())
		    {
		    	throw new NullValueEnteredException("Car Segment is required !!");
		    }
		    if(car.getCarBrand()==null || car.getCarBrand().trim().isEmpty())
		    {
		    	throw new NullValueEnteredException("Car brand name is required !!");
		    }
		    if(car.getCarSeater() <4)
		    {
		    	throw new InvalidDataFormatException("Number of seats should be atleast 4 !!");
		    }
		    if(car.getCarFuel()==null || car.getCarFuel().isEmpty())
		    {
		    	throw new NullValueEnteredException("Fuel type is required !!");	
		    }
		    if(car.getCity()==null || car.getCity().isEmpty())
		    {
		    	throw new NullValueEnteredException("City is required !!");	
		    }
		    if(car.getDailyRate()==null)
		    {
		    	throw new NullValueEnteredException("Daily rent is required !!");
		    }
		    if(car.getDailyRate()<=0)
		    {
		    	throw new InvalidDataFormatException("Daily rent should be positive !!");
		    }
		    if(car.getAvailabilityStatus()==null)
		    {
		    	throw new NullValueEnteredException("Availability status is required !!");	
		    }
		 // Save the car in the repository and return it
			carRepository.save(car);
			return car;
		}
		//remove car
		@Override
		public String deleteCar(int car_id) throws CarNotFoundException{
			log.info("[CarService] deleteCar() : {}",car_id);
			// car not found
		    if (!carRepository.findById(car_id).isPresent()) {
		    	throw new CarNotFoundException ("Car not found !!!");
		    }
			carRepository.deleteById(car_id);
			return "Deleted successfully !!!";
		}
		//update car
		@Override
		public Car editCar(Car car) throws CarNotFoundException{
			log.info("[CarService] editCar() : {}",car);
			int carId=car.getCarId();
			//car not found
			if (!carRepository.findById(carId).isPresent()) {
				throw new CarNotFoundException ("Car not found !!!");
		    }  
			carRepository.save(car);
			return car;
		}
		
									//User
		//view all cars based on city & availability status
		@Override
		public List<Car> showAllCars(String city) {
			log.info("[CarService] showAllCars() ");
			//findByCityAndavailabilityStatusTrue
			return carRepository.findByCityAndAvailabilityStatusTrue(city);
		}

		//search by city
		@Override
		public List<Car> searchByCity(String city) {
			log.info("[CarService] searchByCity() {}",city);
			// TODO Auto-generated method stub
			return showAllCars(city);
		}

		//search by seat
		@Override
		public List<Car> searchBySeater(String city,int seater) {
			log.info("[CarService] searchBySeater() {}",seater);
			List<Car> cars=carRepository.findByCityAndAvailabilityStatusTrue(city);
			List<Car> newCarList=new ArrayList<Car>();
			
			for(Car c:cars)
			{
				if(c.getCarSeater()==seater)
				{
					newCarList.add(c);
				}
			}		
			
			return newCarList;
		}

		//search by brand
		@Override
		public List<Car> searchByBrand(String city,String brand) {
			log.info("[CarService] searchByBrand() {}",brand);
			List<Car> cars=carRepository.findByCityAndAvailabilityStatusTrue(city);
			List<Car> newCarList=new ArrayList<Car>();
			
			for(Car c:cars)
			{
				if(c.getCarBrand().equalsIgnoreCase(brand))
				{
					newCarList.add(c);
				}
			}
			
			return newCarList;
		}

		//search by segment
		@Override
		public List<Car> searchBySegment(String city, String segment) {
			log.info("[CarService] searchBySegment() {}",segment);
			List<Car> cars=showAllCars(city);
			List<Car> newCarList=new ArrayList<Car>();
			
			for(Car c:cars)
			{
				if(c.getCarSegment().equalsIgnoreCase(segment))
				{
					newCarList.add(c);
				}
			}
			
			return newCarList;
		}

		//search by fuel
		@Override
		public List<Car> searchByFuel(String city,String fuel) {
			log.info("[CarService] searchByFuel() {}",fuel);
			List<Car> cars=showAllCars(city);
			List<Car> newCarList=new ArrayList<Car>();
			
			for(Car c:cars)
			{
				if(c.getCarFuel().equalsIgnoreCase(fuel))
				{
					newCarList.add(c);
				}
			}
			
			return newCarList;
		}
		
		public Car getCar(Integer carId) {
			return carRepository.findById(carId).get();
		}

	
}
