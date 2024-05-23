package com.myProject.car_rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.car_rent.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	public List<Car> findByCityAndAvailabilityStatusTrue(String city);

}
