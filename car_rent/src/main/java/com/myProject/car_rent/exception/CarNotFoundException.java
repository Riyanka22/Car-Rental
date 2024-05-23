package com.myProject.car_rent.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarNotFoundException extends Exception {
	private static final long serialVersionUID = 4689948914452042286L;
	private String message;

	public CarNotFoundException() {
		super();
	}

	public CarNotFoundException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

	@Override
	public String getMessage() {
		return message;
	}
}
