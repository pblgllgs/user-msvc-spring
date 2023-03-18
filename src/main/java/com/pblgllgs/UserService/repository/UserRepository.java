package com.pblgllgs.UserService.repository;

import com.pblgllgs.UserService.entity.User;
import com.pblgllgs.UserService.model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(long userId);
}
