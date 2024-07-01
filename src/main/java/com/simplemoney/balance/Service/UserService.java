package com.simplemoney.balance.Service;

import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse fetchUser(String username);
}
