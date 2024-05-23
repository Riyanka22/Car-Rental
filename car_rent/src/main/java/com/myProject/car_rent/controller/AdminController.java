package com.myProject.car_rent.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.repository.CarRepository;
import com.myProject.car_rent.service.AdminService;
import com.myProject.car_rent.service.LoginService;
import com.myProject.car_rent.util.Login;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CarRepository carRepository;

	private static String FOLDER_PATH = "C:\\Project\\car_rent\\car-rental-frontend\\public\\Assets\\";

	// login --> from frontend based on role API will be fetched
	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Login login) throws AdminNotFoundException {
		log.info("[ADMIN] Received request to log in - email {}", login.getEmail());
		return new ResponseEntity<>(loginService.loginAdmin(login), HttpStatus.OK);
	}

	// car
	@GetMapping("/showAllCars")
	public ResponseEntity<List<Car>> showAllCars() {
		log.info("[ADMIN] Received request to show all cars");
		return ResponseEntity.ok(adminService.showAllCars());
	}

	@PostMapping("/addCar")
	public ResponseEntity<Car> addCar(@RequestParam("file") MultipartFile file, @RequestParam("carData") String carData)
			throws CarAreadyPresentException, NullValueEnteredException, InvalidDataFormatException, IOException {

		// converting string to data
		Car car = null;
		try {
			// if we give wrong data that can't be converted to car object
			car = mapper.readValue(carData, Car.class);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
		// the file path of the photo
		String filePath = FOLDER_PATH + car.getCarId() + "_" + file.getOriginalFilename();

		if (carRepository.findById(car.getCarId()) != null) {

			// copying the photo to our folder
			file.transferTo(new File(filePath));
			String fileName= car.getCarId() + "_" + file.getOriginalFilename();
			// setting the file path
			car.setCarPhotoPath(fileName);
		}

		log.info("[ADMIN] Received request to add a car details of car id : {}", car.getCarId());
		log.info("File stored in : {}", filePath);

		return ResponseEntity.ok(adminService.addCar(car));
	}

	@DeleteMapping("/deleteCar/{car_id}")
	public ResponseEntity<String> deleteCar(@PathVariable int car_id) throws CarNotFoundException {
		log.info("[ADMIN] Received request to delete car details of car id : {}", car_id);
		return ResponseEntity.ok(adminService.deleteCar(car_id));
	}

	@PutMapping("/editCar")
	public ResponseEntity<Car> editCar(@RequestBody Car car) throws CarNotFoundException {
		log.info("[ADMIN] Received request to edit car details of car id : {}", car.getCarId());
		return ResponseEntity.ok(adminService.editCar(car));
	}

	// user
	@GetMapping("/showAllUsers")
	public ResponseEntity<List<User>> showAllUsers() {
		log.info("[ADMIN] Received request to show all user details");
		return ResponseEntity.ok(adminService.showAllUsers());
	}

	// booking
	@GetMapping("/showAllBookings")
	public ResponseEntity<List<Booking>> showAllBookings() {
		log.info("[ADMIN] Received request to show all booking details");
		return ResponseEntity.ok(adminService.showAllBookings());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Admin> getUser(@PathVariable String email)throws AdminNotFoundException
	{
		return new ResponseEntity<>(adminService.getAdmin(email), HttpStatus.OK);
	}
	
	//get car
		@GetMapping("/getCar/{carId}")
		public ResponseEntity<Car> getCar(@PathVariable Integer carId)
		{
			return ResponseEntity.ok(adminService.getCar(carId));
		}
	
}
