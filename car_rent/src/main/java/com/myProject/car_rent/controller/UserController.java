package com.myProject.car_rent.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserAreadyPresentException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.service.BookingService;
import com.myProject.car_rent.service.LoginService;
import com.myProject.car_rent.service.UserService;
import com.myProject.car_rent.util.Login;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private BookingService bookingService;
	 
	//login---> from front-end based on role API will be fetched
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody Login login) throws UserNotFoundException
	{
		log.info("[USER] Received request for log in - email : {}",login.getEmail());
		return new ResponseEntity<>(loginService.loginUser(login), HttpStatus.OK);
	}
	
	
	//registration
	@PostMapping("/register")
	public ResponseEntity<User> registration(@RequestBody User user) throws UserAreadyPresentException, InvalidDataFormatException,NullValueEnteredException
	{
		log.info("[USER] Received request for registration - email : {}",user.getUserEmail());
		return new ResponseEntity<>(userService.registration(user), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<User> getUser(@PathVariable String email)throws UserNotFoundException
	{
		return new ResponseEntity<>(userService.getUser(email), HttpStatus.OK);
	}
	
	
	//view All available cars in particular city
	@GetMapping("/showAllCars/{city}")
	public ResponseEntity<List<Car>> showAllCars(@PathVariable String city){
		log.info("[USER] Received request to show all cars");
		List<Car>cars= userService.showAllCars(city);
		return ResponseEntity.ok(cars);
	}
	
	//view booking details of particular user
	@GetMapping("/viewBookingDetails/{userEmail}")
	public ResponseEntity<List<Booking>> viewBookingDetails(@PathVariable String userEmail) throws UserNotFoundException
	{
		log.info("[USER] Received request to view booking details");
	    List<Booking> bookings = userService.viewBookingDetails(userEmail);
	    return ResponseEntity.ok(bookings);
	}
	
	//generate bill
	@PostMapping("/generateBill")
	public ResponseEntity<Booking> generateBill(@RequestBody  Booking booking) throws BookingNotAllowedException{
		log.info("booking obj->",booking);
		Booking booking2=bookingService.generateBill(booking);
		System.out.println(booking2);
		return ResponseEntity.ok(booking2);
	}
	
	//book car
	@PostMapping("/bookCar")
	public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) throws BookingNotAllowedException
	{
		log.info("[USER] Received request for booking");
		return ResponseEntity.ok(userService.bookCar(booking));
	}
	
	//search cars by city
	@GetMapping("/searchByCity/{city}")
	public ResponseEntity<List<Car>> searchByCity(@PathVariable String city)
	{
		log.info("[USER] Received request to search by city");
		List<Car> cars= userService.searchByCity(city);
		return ResponseEntity.ok(cars);
	}
	
	//search cars by seater
	@GetMapping("/searchBySeater/{city}/{seater}")
	public ResponseEntity<List<Car>> searchBySeater(@PathVariable String city,@PathVariable int seater)
	{
		log.info("[USER] Received request to search by seat");
		return ResponseEntity.ok(userService.searchBySeater(city,seater));
	}
	//search cars by brand
	@GetMapping("/searchByBrand/{city}/{brand}")
	public ResponseEntity<List<Car>> searchByBrand(@PathVariable String city,@PathVariable String brand)
	{
		log.info("[USER] Received request to search by brand");
		return ResponseEntity.ok(userService.searchByBrand(city,brand));
	}
	
	//search cars by segment
	@GetMapping("/searchBySegment/{city}/{segment}")
	public ResponseEntity<List<Car>> searchBySegment(@PathVariable String city,@PathVariable String segment)
	{
		log.info("[USER] Received request to search by segment");
		return ResponseEntity.ok(userService.searchBySegment(city,segment));
	}
	
	//search cars by fuel
	@GetMapping("/searchByFuel/{city}/{fuel}")
	public ResponseEntity<List<Car>> searchByFuel(@PathVariable String city,@PathVariable String fuel)
	{
		log.info("[USER] Received request to search by fuel");
		return ResponseEntity.ok(userService.searchByFuel(city,fuel));
	}
	
	
	//get car
	@GetMapping("/getCar/{carId}")
	public ResponseEntity<Car> getCar(@PathVariable Integer carId)
	{
		return ResponseEntity.ok(userService.getCar(carId));
	}
	
}
