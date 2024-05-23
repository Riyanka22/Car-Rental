package com.myProject.car_rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	//to check the orders
	public List<Booking> findByUser(User user);
	
	//for discount on first 5 bookings for each user 
	public int countByUser(User user);

}
