package com.simplemoney.balance.Mapper;

import com.simplemoney.balance.Dto.User;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);
}
