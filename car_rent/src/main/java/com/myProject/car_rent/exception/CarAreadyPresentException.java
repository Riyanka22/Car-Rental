package com.myProject.car_rent.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarAreadyPresentException extends Exception {
	private static final long serialVersionUID = -4742804718073572845L;
	private String message;

	public CarAreadyPresentException() {
		super();
	}

	public CarAreadyPresentException(String message) {
		super();
		this.message = message;
		log.error(message);
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
