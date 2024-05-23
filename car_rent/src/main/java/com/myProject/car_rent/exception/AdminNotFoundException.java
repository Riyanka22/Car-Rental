package com.myProject.car_rent.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

	@Override
	public String getMessage() {
		return message;
	}
}
