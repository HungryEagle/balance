package com.simplemoney.balance.Service.impl;

import com.simplemoney.balance.Dto.db.Role;
import com.simplemoney.balance.Dto.db.UserEntity;
import com.simplemoney.balance.Dto.reponse.UserResponse;
import com.simplemoney.balance.Dto.request.LoginRequest;
import com.simplemoney.balance.Dto.request.UserRequest;
import com.simplemoney.balance.Mapper.UserMapper;
import com.simplemoney.balance.Repository.RoleRepository;
import com.simplemoney.balance.Repository.UserRepository;
import com.simplemoney.balance.Service.UserService;
import com.simplemoney.balance.common.Constants;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        try {
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            UserEntity userEntity = UserMapper.INSTANCE.userRequestToUser(userRequest);
            Role role = roleRepository.getByName("USER");
            userEntity.setRoles(Collections.singletonList(role));
            userRepository.save(userEntity);
            return UserMapper.INSTANCE.userToUserResponse(userEntity);
        } catch (Exception ex) {
            logger.info("Some exception: {}", ex.getStackTrace());
        }
        return null;
    }

    @Override
    public UserResponse fetchUser(String username) {
        try {
            UserEntity userEntity = userRepository.getByUsername(username);
            return UserMapper.INSTANCE.userToUserResponse(userEntity);
        } catch (Exception ex) {
            logger.info("Not found user: {}", ex.getStackTrace());
        }
        return null;
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User signed in successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND);
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), mapRolesToAuthorities(userEntity.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
