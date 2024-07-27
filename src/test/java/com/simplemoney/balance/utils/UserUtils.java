package com.simplemoney.balance.utils;

import com.simplemoney.balance.Dto.UserEntity;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;

import static com.simplemoney.balance.common.TestConstants.*;
import static com.simplemoney.balance.common.TestConstants.EMAIL_ID;

public class UserUtils {

    public UserRequest getUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(USERNAME);
        userRequest.setPassword(PASSWORD);
        userRequest.setEmailId(EMAIL_ID);
        return userRequest;
    }

    public UserEntity getUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(USERNAME);
        userEntity.setPassword(PASSWORD);
        userEntity.setEmailId(EMAIL_ID);
        return userEntity;
    }

    public UserResponse getUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(USERNAME);
        userResponse.setEmailId(EMAIL_ID);
        return userResponse;
    }

}
