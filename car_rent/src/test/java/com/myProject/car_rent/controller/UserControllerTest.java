package com.myProject.car_rent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserAreadyPresentException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.service.LoginService;
import com.myProject.car_rent.service.UserService;
import com.myProject.car_rent.util.Login;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private LoginService loginService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser_success() throws UserNotFoundException {
        String email = "test@example.com";
        Login login = new Login();
        login.setEmail(email);
        User user = new User();
        when(loginService.loginUser(login)).thenReturn(user);

        ResponseEntity<User> response = userController.loginUser(login);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testRegistration_success() throws UserAreadyPresentException, InvalidDataFormatException, NullValueEnteredException {
        User user = new User();
        when(userService.registration(user)).thenReturn(user);

        ResponseEntity<User> response = userController.registration(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testRegistration_failure() throws UserAreadyPresentException, InvalidDataFormatException, NullValueEnteredException {
        User user = new User();
        when(userService.registration(user)).thenThrow(new UserAreadyPresentException("User already exists"));

        assertThrows(UserAreadyPresentException.class, () -> userController.registration(user));
    }

    @Test
    public void testShowAllCars_success() {
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.showAllCars(city)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.showAllCars(city);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testViewBookingDetails_success() throws UserNotFoundException {
        String userEmail = "test@example.com";
        List<Booking> bookings = new ArrayList<>();
        when(userService.viewBookingDetails(userEmail)).thenReturn(bookings);

        ResponseEntity<List<Booking>> response = userController.viewBookingDetails(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());
    }

    @Test
    public void testSaveBooking_success() throws BookingNotAllowedException {
        Booking booking = new Booking();
        Booking savedBooking = new Booking();
        when(userService.bookCar(booking)).thenReturn(savedBooking);

        ResponseEntity<Booking> response = userController.saveBooking(booking);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedBooking, response.getBody());
    }

    @Test
    public void testSearchByCity_success() {
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.searchByCity(city)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.searchByCity(city);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testSearchBySeater_success() {
        int seater = 4;
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.searchBySeater(city, seater)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.searchBySeater(city, seater);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testSearchByBrand_success() {
        String brand = "Toyota";
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.searchByBrand(city, brand)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.searchByBrand(city, brand);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testSearchBySegment_success() {
        String segment = "SUV";
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.searchBySegment(city, segment)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.searchBySegment(city, segment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testSearchByFuel_success() {
        String fuel = "Gasoline";
        String city = "New York";
        List<Car> cars = new ArrayList<>();
        when(userService.searchByFuel(city, fuel)).thenReturn(cars);

        ResponseEntity<List<Car>> response = userController.searchByFuel(city, fuel);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }
}