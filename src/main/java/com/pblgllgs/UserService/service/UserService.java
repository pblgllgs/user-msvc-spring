package com.pblgllgs.UserService.service;

import com.pblgllgs.UserService.model.UserRequest;
import com.pblgllgs.UserService.model.UserResponse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    Long save(UserRequest userRequest);

    UserResponse getUserById(long userId);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(long userId, UserRequest userRequest);
}
