package com.myProject.car_rent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice(basePackages = "com.myProject.car_rent.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NullValueEnteredException.class)
	public ResponseEntity<String> handleNullValueEnteredException(NullValueEnteredException exception) {
		log.error("Handling NullValueEnteredException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The value entered is empty";
		}

		return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(value = InvalidDataFormatException.class)
	public ResponseEntity<String> handleInvalidDataFormatException(InvalidDataFormatException exception) {
		log.error("Handling InvalidDataFormatException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The entered value does not match expected input !!";
		}

		return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(value = UserAreadyPresentException.class)
	public ResponseEntity<String> handleUserAreadyPresentException(UserAreadyPresentException exception) {
		log.error("Handling UserAreadyPresentException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "User already registered with given email id !!";
		}

		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
		log.error("Handling UserNotFoundException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "User does not exist with given email id !!";
		}

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AdminNotFoundException.class)
	public ResponseEntity<String> handleAdminNotFoundException(AdminNotFoundException exception) {
		log.error("Handling AdminNotFoundException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Admin does not exist with given email id !!";
		}

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CarNotFoundException.class)
	public ResponseEntity<String> handleCarNotFoundException(CarNotFoundException exception) {
		log.error("Handling CarNotFoundException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Car not found !!";
		}

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CarAreadyPresentException.class)
	public ResponseEntity<String> handleCarAreadyPresentException(CarAreadyPresentException exception) {
		log.error("Handling CarAreadyPresentException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Car already registered !!";
		}

		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = BookingNotAllowedException.class)
	public ResponseEntity<String> handleBookingNotAllowedException(BookingNotAllowedException exception) {
		log.error("Handling BookingNotAllowedException: {}", exception.getMessage());
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Booking Not possible !!";
		}

		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}
}
