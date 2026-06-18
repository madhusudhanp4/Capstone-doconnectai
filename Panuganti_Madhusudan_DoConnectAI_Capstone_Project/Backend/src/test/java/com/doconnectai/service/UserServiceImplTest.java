package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.doconnectai.dto.UserDto;
import com.doconnectai.entity.Role;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.repository.UserRepo;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private BCryptPasswordEncoder pswrdEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    
    private UserDto userDto;

    
    
    
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Madhu");
        user.setEmail("madhu@gmail.com");
        user.setPassword("encoded");
        user.setRole(Role.USER);

        userDto = new UserDto();
        userDto.setName("Madhu");
        userDto.setEmail("madhu@gmail.com");
        userDto.setPassword("raw123");
    }
    
    
    
    
    
    
    
    @Test
    void testRegisterUser() {

        when(pswrdEncoder.encode("raw123")).thenReturn("encoded");
        when(userRepo.save(any(User.class))).thenReturn(user);

        UserDto result = userService.registerUser(userDto);

        assertNotNull(result);
        assertEquals("madhu@gmail.com", result.getEmail());

        verify(pswrdEncoder).encode("raw123");
        verify(userRepo).save(any(User.class));
    }
    
    
    

    @Test
    void testGetUserById() {

        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        UserDto result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("madhu@gmail.com", result.getEmail());
    }

    
    
    
    
    
    @Test
    void testGetUserById_NotFound() {

        when(userRepo.findById(2)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(2);
        });
    }
    
    
    
    
    

    @Test
    void testDeleteUser() {

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(userRepo).delete(user);

        userService.deleteUser(1);

        verify(userRepo).delete(user);
    }
}
