package com.simplemoney.balance.Controller;

import com.simplemoney.balance.Dto.reponse.Response;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;
import com.simplemoney.balance.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Response<UserResponse>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        if (null == userResponse) {
            return new ResponseEntity<>(new Response<>(false, null, "You are a fool"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new Response<>(true, userResponse), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<Response<UserResponse>> fetchUser(@PathVariable String username) {
        UserResponse userResponse = userService.fetchUser(username);
        if (null == userResponse) {
            return new ResponseEntity<>(new Response<>(false, null, "User not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new Response<>(true, userResponse, "User found"), HttpStatus.OK);
    }
}
