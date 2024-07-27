package com.simplemoney.balance.Service.impl;

import com.simplemoney.balance.Dto.Role;
import com.simplemoney.balance.Dto.UserEntity;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Repository.RoleRepository;
import com.simplemoney.balance.Repository.UserRepository;
import com.simplemoney.balance.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;
import static com.simplemoney.balance.common.TestConstants.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserUtils userUtils;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userUtils = new UserUtils();
    }

    @Test
    public void createUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userUtils.getUser());
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("password!@#!@#");
        when(roleRepository.getByName(any())).thenReturn(new Role());
        UserResponse userResponse = userServiceImpl.createUser(userUtils.getUserRequest());
        Assert.assertEquals(userResponse.getUsername(), userUtils.getUser().getUsername());
    }

    @Test
    public void createUserWithException() {
        when(userRepository.save(any(UserEntity.class))).thenThrow(new RuntimeException());
        UserResponse userResponse = userServiceImpl.createUser(userUtils.getUserRequest());
        assertNull(userResponse);
    }

    @Test
    public void fetchUser() {
        when(userRepository.getByUsername(USERNAME)).thenReturn(userUtils.getUser());
        UserResponse userResponse = userServiceImpl.fetchUser(USERNAME);
        Assert.assertEquals(userResponse.getUsername(), userUtils.getUser().getUsername());
    }

    @Test
    public void fetchUserWithException() {
        when(userRepository.getByUsername(USERNAME)).thenThrow(new RuntimeException());
        UserResponse userResponse = userServiceImpl.fetchUser(USERNAME);
        assertNull(userResponse);
    }
}