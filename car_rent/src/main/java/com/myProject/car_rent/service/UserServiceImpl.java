package com.myProject.car_rent.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserAreadyPresentException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.helpermodule.InputValidator;
import com.myProject.car_rent.repository.BookingRepository;
import com.myProject.car_rent.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private CarService carService;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingService bookingService;
	
	private BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
	
	//registration for user
	@Override
	public User registration(User user) throws UserAreadyPresentException, InvalidDataFormatException,NullValueEnteredException
	{
		
		
		log.info("[UserService] register - user : {}",user);
		//name
		if(user.getFirstName()==null || user.getFirstName().trim().isEmpty())
		{
			throw new NullValueEnteredException("First name is required !!");
		}
		//email
		if (user.getUserEmail().trim() == null || user.getUserEmail().trim().isEmpty()) {
			throw new NullValueEnteredException("Email id is required !!");
		}
		//changing email to lowercase since email case in sensitive
		user.setUserEmail(user.getUserEmail().toLowerCase());
		if(!InputValidator.isValidEmail(user.getUserEmail()))//email is not correct
		{
			throw new InvalidDataFormatException("Invalid email !!");
		}
		if(userRepository.existsById(user.getUserEmail()))//already registered
		{
			throw new UserAreadyPresentException("Email already registered !!");
		}
		//phone
		if(user.getPhoneNumber()==null || user.getPhoneNumber().isEmpty())
		{
			throw new NullValueEnteredException("Phone number is required !!");
		}
		if(!InputValidator.isValidPhoneNumber(user.getPhoneNumber()))
		{
			throw new InvalidDataFormatException("Invalid phone number !!");
		}
		//driving license
		if(user.getDrivingLicense()==null || user.getDrivingLicense().isEmpty())
		{
			throw new NullValueEnteredException("Driving license is required !!");
		}
		if(user.getDrivingLicense().length()!=10) //driving license not correct
		{
			throw new InvalidDataFormatException("Driving license must be 10 characters long !!");
		}
		//dob
		if(user.getDateOfBirth()==null)
		{
			throw new NullValueEnteredException("Date of birth is required !!");
		}
		if(user.getDateOfBirth().until(LocalDate.now()).getYears()<18) //age should be atleast 18
		{
			throw new InvalidDataFormatException("Age must be 18 or above !!");
		}
		//password
		if(user.getPassword()==null || user.getPassword().isEmpty())
		{
			throw new NullValueEnteredException("Password is required !!");
		}
		if(!InputValidator.isValidPassword(user.getPassword())) //invalid password
		{
			throw new InvalidDataFormatException("Invalid password !!");
		}
		// Trimming space
		String fname=user.getFirstName().trim();
		user.setFirstName(fname);
		String lname=user.getLastName().trim();
		user.setLastName(lname);
		
		//trimming email
		String email=user.getUserEmail().trim();
		user.setUserEmail(email);
		
		//encrypting the password 
		String rawPasswordString=user.getPassword();
		String encrypedPassword=passwordEncoder.encode(rawPasswordString);
		user.setPassword(encrypedPassword);
		user.setRegistrationDate(LocalDate.now()); //registration date is present day
		return userRepository.save(user);
	}

	//view user's booking details
	@Override
	public List<Booking> viewBookingDetails(String userEmail)throws UserNotFoundException {
		
		log.info("[UserService] view booking details - user email : {}",userEmail);
		
		if(!userRepository.existsById(userEmail))//not present
		{
			throw new UserNotFoundException("User NOT found !!");
		}
		
		User user=userRepository.findById(userEmail).get();
		return bookingRepository.findByUser(user);
	}
	
	//book a car
	public Booking bookCar(Booking booking) throws BookingNotAllowedException
	{
		log.info("[UserService] booking car - booking : {}",booking);
		return bookingService.bookCar(booking);
	}
	
	//view all cars based on city & availability status
	@Override
	public List<Car> showAllCars(String city) {
		log.info("[UserService] show all cars");
		//findByCityAndavailabilityStatusTrue
		return carService.showAllCars(city);
	}

	//search by city
	@Override
	public List<Car> searchByCity(String city) {
		log.info("[UserService] search by city : {}",city);
		// TODO Auto-generated method stub
		return showAllCars(city);
	}

	//search by seat
	@Override
	public List<Car> searchBySeater(String city,int seater) {
		log.info("[UserService] search by seater : {}",seater);
		return carService.searchBySeater(city, seater);
	}

	//search by brand
	@Override
	public List<Car> searchByBrand(String city,String brand) {
		log.info("[UserService] ");
		return carService.searchByBrand(city, brand);
	}

	//search by segment
	@Override
	public List<Car> searchBySegment(String city, String segment) {
		log.info("[UserService] search by segment",segment);
		return carService.searchBySegment(city, segment);
	}

	//search by fuel
	@Override
	public List<Car> searchByFuel(String city,String fuel) {
		log.info("[UserService] search by fuel type",fuel);
		return carService.searchByFuel(city, fuel);
	}
	
	//for admin
	//see all users
	public List<User> showAllUsers()
	{
		log.info("[UserService] show all users");
		List<User> users= userRepository.findAll();
		return users;
	}
	
	public User getUser(String email) throws UserNotFoundException
	{
		User user=userRepository.findById(email).orElse(null);
		if(user==null)
			throw new UserNotFoundException("User does not exist !");
		return user;
	}
	
	public Car getCar(Integer carId) {
		return carService.getCar(carId);
	}
	
}
