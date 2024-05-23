package com.myProject.car_rent.service;

import java.util.List;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.exception.BookingNotAllowedException;

public interface BookingService {
	
	//add booking data
	public Booking bookCar(Booking booking)throws BookingNotAllowedException;
	//calculate the bill
	public Booking generateBill(Booking booking) throws BookingNotAllowedException ;
	//give discount
	public double discountOnFirstFiveBooking(Booking booking);
	//show all bookings 
	public List<Booking> showAllBookings();
	
}
