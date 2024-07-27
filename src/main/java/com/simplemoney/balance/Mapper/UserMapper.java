package com.simplemoney.balance.Mapper;

import com.simplemoney.balance.Dto.db.UserEntity;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(UserEntity userEntity);
}
