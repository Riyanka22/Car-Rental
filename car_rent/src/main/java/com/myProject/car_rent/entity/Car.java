package com.myProject.car_rent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
public class Car {
	@Id
	@Column(name = "car_id")
	private Integer carId ;
	
	@Column(name = "carPhotoPath")
	private String carPhotoPath ;
	
	@Column(name = "car_segment",nullable = false)
	private String carSegment;
	
	@Column(name = "car_brand",nullable = false)
	private String carBrand;

	@Column(name = "car_seater",nullable = false)
	private Integer carSeater;
	
	@Column(name = "car_fuel",nullable = false)
	private String carFuel;
	
	@Column(nullable = false)
	private String city ;
	
	@Column(name = "daily_rate", nullable=false)
	private Double dailyRate;
	
	@Column(name = "availability_status",columnDefinition = "boolean", nullable=false)
	private Boolean availabilityStatus;

}
