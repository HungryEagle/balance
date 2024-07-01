package com.simplemoney.balance.Service.impl;

import com.simplemoney.balance.Dto.User;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;
import com.simplemoney.balance.Mapper.UserMapper;
import com.simplemoney.balance.Repository.UserRepository;
import com.simplemoney.balance.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        try {
            User user = UserMapper.INSTANCE.userRequestToUser(userRequest);
            userRepository.save(user);
            return UserMapper.INSTANCE.userToUserResponse(user);
        } catch (Exception ex) {
            logger.info("Some exception: {}", ex.getStackTrace());
        }
        return null;
    }

    @Override
    public UserResponse fetchUser(String username) {
        try {
            User user = userRepository.getByUsername(username);
            return UserMapper.INSTANCE.userToUserResponse(user);
        } catch (Exception ex) {
            logger.info("Not found user: {}", ex.getStackTrace());
        }
        return null;
    }
}
