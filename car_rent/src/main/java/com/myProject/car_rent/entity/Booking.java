package com.myProject.car_rent.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bookings")
public class Booking {
	@Id
	@GeneratedValue
	@Column(name="booking_id")
	private int bookingId;
	
	//fetch = FetchType.LAZY ->User & Car entity will not be loaded unless I access them explicitly
	//insertable / updatable -> we can not update or insert new user_id & car_id while dealing with booking table
	
	@ManyToOne//one user can have multiple booking but one booking belongs to one user only
	@JoinColumn(name="user_email" ,nullable = false)
	private User user;
	
	@ManyToOne// one car may be booked multiple times but one booking is for one car only
	@JoinColumn(name = "car_id",nullable = false)
	private Car car;
	
	@Column(name = "booking_date",nullable = false)
	private LocalDate bookingDate;

	
	@Column(name = "booking_start_date",nullable = false)
	private LocalDate bookingStartDate;
	
	@Column(name = "booking_end_date",nullable = false)
	private LocalDate bookingEndDate;
	
	@Column(name = "pickup_location",nullable = false)
	private String pickupLocation;
	
	@Column(name = "return_location",nullable = false)
	private String returnLocation;
	
	@Column(name = "base_fare",nullable = false)
	private Double baseFare;
	
	//make this static
	@Column(name="security_deposit",nullable = false)
	private Double securityDeposit; //20%
	
	//make this static
	@Column(name = "delivery_and_pickup_charge",nullable = false)
	private Double deliveryAndPickupCharge; //10%
	
	//discount amount
	@Column
	private Double discount;
	
	//total payable
	@Column(name = "total_amount",nullable = false)
	private Double totalAmount;

	//constructor	
	public Booking( User user, Car car, LocalDate bookingStartDate, LocalDate bookingEndDate,
			String pickupLocation, String returnLocation) {
		super();
		this.user = user;
		this.car = car;
		this.bookingStartDate = bookingStartDate;
		this.bookingEndDate = bookingEndDate;
		this.pickupLocation = pickupLocation;
		this.returnLocation = returnLocation;
	}
		
}
