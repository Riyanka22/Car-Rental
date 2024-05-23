
package com.myProject.car_rent.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private CarService carService;

    @Mock
    private BookingService bookingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowAllCars() {
        
        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(new Car());
        expectedCars.add(new Car());

        Mockito.when(carService.showAllCarsForAdmin()).thenReturn(expectedCars);
        
        List<Car> actualCars = adminServiceImpl.showAllCars();

        Assertions.assertEquals(expectedCars, actualCars);
    }

    @Test
    public void testAddCar() throws CarAreadyPresentException, NullValueEnteredException, InvalidDataFormatException {
        
        Car car = new Car();
        Car addedCar = new Car();

        
        Mockito.when(carService.addCar(car)).thenReturn(addedCar);

        
        Car actualCar = adminServiceImpl.addCar(car);

        
        Assertions.assertEquals(addedCar, actualCar);
    }

    @Test
    public void testDeleteCar() throws CarNotFoundException {
        
        int carId = 1;
        String expectedMessage = "Car deleted with ID: 1";

        
        Mockito.when(carService.deleteCar(carId)).thenReturn(expectedMessage);

        
        String actualMessage = adminServiceImpl.deleteCar(carId);

        
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testEditCar() throws CarNotFoundException {
        
        Car car = new Car();
        Car updatedCar = new Car();

        
        Mockito.when(carService.editCar(car)).thenReturn(updatedCar);

        
        Car actualCar = adminServiceImpl.editCar(car);

        
        Assertions.assertEquals(updatedCar, actualCar);
    }

    @Test
    public void testShowAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());

        Mockito.when(userService.showAllUsers()).thenReturn(expectedUsers);
       
        List<User> actualUsers = adminServiceImpl.showAllUsers();

   
        Assertions.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testShowAllBookings() {
      
        List<Booking> expectedBookings = new ArrayList<>();
        expectedBookings.add(new Booking());
        expectedBookings.add(new Booking());

        Mockito.when(bookingService.showAllBookings()).thenReturn(expectedBookings);

        
        List<Booking> actualBookings = adminServiceImpl.showAllBookings();

        Assertions.assertEquals(expectedBookings, actualBookings);
    }
}