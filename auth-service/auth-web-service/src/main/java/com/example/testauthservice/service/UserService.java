package com.example.testauthservice.service;

import com.example.testauthservice.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    UserEntity createUser(UserEntity user);

    Optional<UserEntity> getUserByUsername(String username);

    Optional<UserEntity> getUserById(Long id);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

}
