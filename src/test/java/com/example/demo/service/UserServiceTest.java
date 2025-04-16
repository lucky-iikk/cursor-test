package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
    
    @Test
    void shouldReturnUserWhenUserExists() {
        // Given
        User expectedUser = new User(1L, "张三");
        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
        
        // When
        User actualUser = userService.getUserById(1L);
        
        // Then
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getName()).isEqualTo("张三");
    }
    
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        
        // When/Then
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });
    }
} 