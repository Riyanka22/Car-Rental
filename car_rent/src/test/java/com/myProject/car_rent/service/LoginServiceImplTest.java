package com.myProject.car_rent.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myProject.car_rent.entity.Admin;
import com.myProject.car_rent.entity.User;
import com.myProject.car_rent.exception.AdminNotFoundException;
import com.myProject.car_rent.exception.UserNotFoundException;
import com.myProject.car_rent.repository.AdminRepository;
import com.myProject.car_rent.repository.UserRepository;
import com.myProject.car_rent.util.Login;

public class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
 

    @Test
    public void testLoginUserFailure() throws UserNotFoundException {
        Login login = new Login("test@test.com", "wrongpassword");
        User user = new User();
        user.setUserEmail("test@test.com");
        user.setPassword(passwordEncoder.encode("password"));

        when(userRepository.findById("test@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", user.getPassword())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> loginService.loginUser(login));
    }

    @Test
    public void testLoginAdminSuccess() throws AdminNotFoundException {
        Login login = new Login("admin@test.com", "adminpassword");
        Admin admin = new Admin();
        admin.setEmail("admin@test.com");
        admin.setPassword("adminpassword");

        when(adminRepository.findById("admin@test.com")).thenReturn(Optional.of(admin));

        Admin result = loginService.loginAdmin(login);

        assertEquals("admin@test.com", result.getEmail());
    }

    @Test
    public void testLoginAdminFailure() throws AdminNotFoundException {
        Login login = new Login("admin@test.com", "wrongpassword");
        Admin admin = new Admin();
        admin.setEmail("admin@test.com");
        admin.setPassword("adminpassword");

        when(adminRepository.findById("admin@test.com")).thenReturn(Optional.of(admin));

        assertThrows(AdminNotFoundException.class, () -> loginService.loginAdmin(login));
    }
}
