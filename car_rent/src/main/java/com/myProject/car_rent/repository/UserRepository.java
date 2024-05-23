package com.myProject.car_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.car_rent.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
