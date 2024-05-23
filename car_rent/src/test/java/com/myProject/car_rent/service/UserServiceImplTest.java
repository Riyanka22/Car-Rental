package com.myProject.car_rent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myProject.car_rent.entity.Booking;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.exception.UserAreadyPresentException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.repository.BookingRepository;
import com.myProject.car_rent.repository.UserRepository;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private CarService carService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Booking booking;
    private List<User> users;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User("user1@example.com", "John", "Doe", "1234567890", LocalDate.of(1990, 1, 1), "ABCDE12345",
                "Password123@");
        booking = new Booking();
        users = new ArrayList<>();
        users.add(user);
        bookings = new ArrayList<>();
        bookings.add(booking);
    }

    @Test
    void testRegistration_validUser_shouldSaveUser() throws UserAreadyPresentException, InvalidDataFormatException,
            NullValueEnteredException {
        when(userRepository.existsById(user.getUserEmail())).thenReturn(false);
       
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        User savedUser = userService.registration(user);

//        assertEquals(user.getUserEmail(), savedUser.getUserEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegistration_existingUser_shouldThrowUserAreadyPresentException()
            throws InvalidDataFormatException, NullValueEnteredException {
        when(userRepository.existsById(user.getUserEmail())).thenReturn(true);

        assertThrows(UserAreadyPresentException.class, () -> userService.registration(user));

        verify(userRepository, never()).save(user);
    }
    

    @Test
    void testViewBookingDetails_validUser_shouldReturnUserBookings() throws UserNotFoundException {
        when(userRepository.existsById(user.getUserEmail())).thenReturn(true);
        when(userRepository.findById(user.getUserEmail())).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(bookings);

        List<Booking> result = userService.viewBookingDetails(user.getUserEmail());

        assertEquals(bookings, result);

        verify(userRepository, times(1)).findById(user.getUserEmail());
        verify(bookingRepository, times(1)).findByUser(user);
    }

    @Test
    void testViewBookingDetails_invalidUser_shouldThrowUserNotFoundException() {
        when(userRepository.existsById(user.getUserEmail())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.viewBookingDetails(user.getUserEmail()));

        verify(userRepository, times(1)).existsById(user.getUserEmail());
        verify(userRepository, never()).findById(user.getUserEmail());
        verify(bookingRepository, never()).findByUser(user);
    }

   
}