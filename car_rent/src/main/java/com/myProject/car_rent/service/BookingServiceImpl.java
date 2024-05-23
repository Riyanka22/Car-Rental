package com.myProject.car_rent.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.repository.BookingRepository;
import com.myProject.car_rent.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public Booking bookCar(Booking booking) throws BookingNotAllowedException {
		log.info("[BookingService] bookCar() : {}",booking);
		
		
		//generate bill
		Booking booked=generateBill(booking);
		
		Car bookedCar=booking.getCar();
		Car actualCar=carRepository.getReferenceById(bookedCar.getCarId());
		//car is now not available
		actualCar.setAvailabilityStatus(false);
		
		//adding the booking details in database
		return bookingRepository.save(booked);
	}

	@Override
	public Booking generateBill(Booking booking) throws BookingNotAllowedException  {
		log.info("[BookingService] generateBill() : {}",booking);
		
		//if car not available no booking
		
		Car bookedCar=booking.getCar();
		
		if(booking.getUser()==null)
		{
			throw new BookingNotAllowedException("User is required !!");
		}
		if(booking.getCar()==null)
		{
			throw new BookingNotAllowedException("Car is required !!");
		}
		if(booking.getBookingStartDate()==null)
		{
			throw new BookingNotAllowedException("Booking start date is required !!");
		}
		if(booking.getBookingStartDate().isBefore(LocalDate.now()))
		{
			throw new BookingNotAllowedException("Booking date cannot be before today !!");
		}
		if(booking.getBookingEndDate()==null)
		{
			throw new BookingNotAllowedException("Booking end date is required !!");
		}
		if(booking.getBookingEndDate().isBefore(booking.getBookingStartDate()))
		{
			throw new BookingNotAllowedException("Booking date cannot be before "+booking.getBookingStartDate()+" !!");
		}
		Car actualCar=carRepository.getReferenceById(bookedCar.getCarId());
		if(actualCar.getAvailabilityStatus()==false)
		{
			throw new BookingNotAllowedException("Car is Not available !!");
		}
		if(booking.getPickupLocation().trim()==null || booking.getPickupLocation().trim().isEmpty())
		{
			throw new BookingNotAllowedException("Pickup location is required !!");
		}
		if(booking.getReturnLocation().trim()==null || booking.getReturnLocation().trim().isEmpty())
		{
			throw new BookingNotAllowedException("Return location is required !!");
		}
		
		booking.setBookingDate(LocalDate.now());
		
		//daily rent
		double carRent=booking.getCar().getDailyRate();
		//no. of days booked
		int daysBooked=booking.getBookingEndDate().getDayOfYear()- booking.getBookingStartDate().getDayOfYear()+1;
		//base fare
		double baseFare=daysBooked * carRent;
		booking.setBaseFare(baseFare);
		
		//security deposit
		double securityDeposit=calculateSecurityDeposit(daysBooked,baseFare);
		booking.setSecurityDeposit(securityDeposit);
		
		// delivery and pickup charge->10%
		double deliveryAndPickupCharge=baseFare*0.10;
		booking.setDeliveryAndPickupCharge(deliveryAndPickupCharge);
		
		//apply discount 
		double discount=discountOnFirstFiveBooking(booking);
		booking.setDiscount(discount);
		
		//total amount
		double totalAmt=baseFare+booking.getSecurityDeposit()+booking.getDeliveryAndPickupCharge()-discount;
		booking.setTotalAmount(totalAmt);
		
		return booking;
	}

	//discount for first five booking for each user
	@Override
	public double discountOnFirstFiveBooking(Booking booking) {
		
		double discount=0;
		if(bookingRepository.countByUser(booking.getUser())<5)
		{
			//15% discount 
			discount= booking.getBaseFare()*0.15;
			
		}
		return discount;
	}
	
	//calculate security amount
	public double calculateSecurityDeposit(int daysBooked,double basefare)
	{
		//by default 500 if booked for more days 10% of base fare
		double securityDeposit=500;
		if(daysBooked>4)
		{
			securityDeposit+=basefare * 0.1;
		}
		return securityDeposit;
	}
	
	//show all bookings
	@Override
	public List<Booking> showAllBookings()
	{
		List<Booking> bookings= bookingRepository.findAll();
		return bookings;
	}
}
