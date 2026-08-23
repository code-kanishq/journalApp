package com.springboot.journalApp.service;

import com.springboot.journalApp.entity.User;
import com.springboot.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests
{
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsername()
    {
        User mockUser = User.builder()
                .userName("Ram")
                .password("qwerty")
                .roles(new ArrayList<>())
                .build();

        when(userRepo.findByUserName(anyString())).thenReturn(mockUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(userDetails);
    }
}