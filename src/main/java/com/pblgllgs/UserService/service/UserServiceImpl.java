package com.pblgllgs.UserService.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pblgllgs.UserService.entity.User;
import com.pblgllgs.UserService.model.UserRequest;
import com.pblgllgs.UserService.model.UserResponse;
import com.pblgllgs.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public Long save(UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
        userRepository.save(user);
        log.info("User created with id: {}",user.getId());
        return user.getId();
    }

    @Override
    public UserResponse getUserById(long userId) {
        User user = userRepository.findUserById(userId);
        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .build();
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> {
            UserResponse ur = new UserResponse();
            BeanUtils.copyProperties(user, ur);
            log.info("user: {}", user);
            return ur;
                }).collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public UserResponse updateUser(long userId, UserRequest userRequest) {
        User user = userRepository.findUserById(userId);
        if (user == null){
            throw new RuntimeException("no existe el usuario");
        }
        user = User.builder()
                .id(userId)
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        userRepository.save(user);
        return userResponse;
    }
}
