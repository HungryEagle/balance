package com.simplemoney.balance.Service.impl;

import com.simplemoney.balance.Dto.db.User;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Repository.UserRepository;
import com.simplemoney.balance.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static com.simplemoney.balance.common.TestConstants.*;
import static org.junit.Assert.*;


public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private UserUtils userUtils;

    @Before
    public void setUp(){
        userServiceImpl = new UserServiceImpl();
        userUtils = new UserUtils();
        userRepository = Mockito.mock(UserRepository.class);
        ReflectionTestUtils.setField(userServiceImpl, USER_REPOSITORY, userRepository);
    }

    @Test
    public void createUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userUtils.getUser());
        UserResponse userResponse = userServiceImpl.createUser(userUtils.getUserRequest());
        Assert.assertEquals(userResponse.getUsername(), userUtils.getUser().getUsername());
    }

    @Test
    public void createUserWithException() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new RuntimeException());
        UserResponse userResponse = userServiceImpl.createUser(userUtils.getUserRequest());
        assertNull(userResponse);
    }

    @Test
    public void fetchUser() {
        Mockito.when(userRepository.getByUsername(USERNAME)).thenReturn(userUtils.getUser());
        UserResponse userResponse = userServiceImpl.fetchUser(USERNAME);
        Assert.assertEquals(userResponse.getUsername(), userUtils.getUser().getUsername());
    }

    @Test
    public void fetchUserWithException() {
        Mockito.when(userRepository.getByUsername(USERNAME)).thenThrow(new RuntimeException());
        UserResponse userResponse = userServiceImpl.fetchUser(USERNAME);
        assertNull(userResponse);
    }
}