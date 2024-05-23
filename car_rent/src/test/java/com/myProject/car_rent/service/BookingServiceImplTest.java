package com.myProject.car_rent.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.BookingNotAllowedException;
import com.myProject.car_rent.repository.BookingRepository;
import com.myProject.car_rent.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Car car;
    private User user;
    private Booking booking;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        car = new Car();
        car.setCarId(1);
        car.setDailyRate(200.0);
        car.setAvailabilityStatus(true);
        user = new User();
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        booking = new Booking(1,user, car,LocalDate.now(), startDate, endDate, "pickup", "return",100.0,1000.0,500.0,20.0,2234.0);
    }

    @Test
    public void testBookCar_ValidBooking() throws BookingNotAllowedException {
        // Arrange
        
        when(carRepository.getReferenceById(1)).thenReturn(car);

        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        
        // Act
        Booking result = bookingService.bookCar(booking);

        // Assert
        verify(carRepository, times(1)).getReferenceById(1);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void testBookCar_InvalidCar() throws BookingNotAllowedException {
        // Arrange
        
        car.setCarId(1);
        car.setAvailabilityStatus(false);
        when(carRepository.getReferenceById(1)).thenReturn(car);

        // Act and Assert
        assertThrows(BookingNotAllowedException.class, () -> bookingService.bookCar(booking));
        verify(carRepository, times(1)).getReferenceById(1);
        verify(bookingRepository, times(0)).save(any(Booking.class));
    }

    @Test
    public void testGenerateBill_ValidBooking() throws BookingNotAllowedException {
        // Arrange
        car.setCarId(1);
        car.setDailyRate(10.0);
        when(carRepository.getReferenceById(1)).thenReturn(car);

        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Act
        Booking result = bookingService.bookCar(booking);

        // Assert
        verify(carRepository, times(1)).getReferenceById(1);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void testShowAllBookings() {
        // Arrange
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.showAllBookings();

        // Assert
        verify(bookingRepository, times(1)).findAll();
//        assertEquals(bookings, result);
    }
}