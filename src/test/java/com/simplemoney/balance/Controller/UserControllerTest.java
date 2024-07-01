package com.simplemoney.balance.Controller;

import com.simplemoney.balance.Dto.reponse.Response;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;
import com.simplemoney.balance.Service.UserService;
import com.simplemoney.balance.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Objects;

import static com.simplemoney.balance.common.Constants.USERNAME;
import static com.simplemoney.balance.common.Constants.USER_SERVICE;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserUtils userUtils;

    @Before
    public void setUp(){
        userController = new UserController();
        userUtils = new UserUtils();
        userService = Mockito.mock(UserService.class);
        ReflectionTestUtils.setField(userController, USER_SERVICE, userService);
    }

    @Test
    public void createUser() {
        Mockito.when(userService.createUser(Mockito.any(UserRequest.class))).thenReturn(userUtils.getUserResponse());
        ResponseEntity<Response<UserResponse>> responseEntity = userController.createUser(userUtils.getUserRequest());
        Assert.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getData().getUsername(),
                userUtils.getUserResponse().getUsername());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertTrue(responseEntity.getBody().isSuccess());
    }

    @Test
    public void createUserWithException() {
        Mockito.when(userService.createUser(Mockito.any(UserRequest.class))).thenReturn(null);
        ResponseEntity<Response<UserResponse>> responseEntity = userController.createUser(userUtils.getUserRequest());
        Assert.assertNull(Objects.requireNonNull(responseEntity.getBody()).getData());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
        Assert.assertFalse(responseEntity.getBody().isSuccess());
    }

    @Test
    public void testFetchUser() {
        Mockito.when(userService.fetchUser(Mockito.anyString())).thenReturn(userUtils.getUserResponse());
        ResponseEntity<Response<UserResponse>> responseEntity = userController.fetchUser(USERNAME);
        Assert.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getData().getUsername(),
                userUtils.getUserResponse().getUsername());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());
    }

    @Test
    public void testFetchUserWithException() {
        Mockito.when(userService.fetchUser(Mockito.anyString())).thenReturn(null);
        ResponseEntity<Response<UserResponse>> responseEntity = userController.fetchUser(USERNAME);
        Assert.assertNull(Objects.requireNonNull(responseEntity.getBody()).getData());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
        Assert.assertFalse(responseEntity.getBody().isSuccess());
    }
}