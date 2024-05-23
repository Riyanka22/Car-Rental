package com.myProject.car_rent.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingNotAllowedException extends Exception {
	private static final long serialVersionUID = 8442408368460471698L;
	private String message;
	
	public BookingNotAllowedException() {
		super();
	}

	public BookingNotAllowedException(String message) {
		super();
		this.message = message;
		log.error(message);
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
