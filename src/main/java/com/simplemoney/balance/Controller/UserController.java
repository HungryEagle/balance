package com.simplemoney.balance.Controller;

import com.simplemoney.balance.Dto.reponse.Response;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.LoginRequest;
import com.simplemoney.balance.Dto.request.UserRequest;
import com.simplemoney.balance.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/auth/user", method = RequestMethod.POST)
    public ResponseEntity<Response<UserResponse>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponseFetch = userService.fetchUser(userRequest.getUsername());
        if(userResponseFetch != null) {
            return new ResponseEntity<>(new Response<>(false, null, "Username is taken"), HttpStatus.BAD_REQUEST);
        }
        UserResponse userResponse = userService.createUser(userRequest);
        if (null == userResponse) {
            return new ResponseEntity<>(new Response<>(false, null, "You are a fool"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new Response<>(true, userResponse), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/auth/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<Response<UserResponse>> fetchUser(@PathVariable String username) {
        UserResponse userResponse = userService.fetchUser(username);
        if (null == userResponse) {
            return new ResponseEntity<>(new Response<>(false, null, "User not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new Response<>(true, userResponse, "User found"), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public ResponseEntity<Response<String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        String response = userService.loginUser(loginRequest);
        return new ResponseEntity<>(new Response<>(true, response, "User found"), HttpStatus.OK);
    }
}
