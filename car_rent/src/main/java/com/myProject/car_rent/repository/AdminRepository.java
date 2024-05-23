package com.myProject.car_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.car_rent.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
