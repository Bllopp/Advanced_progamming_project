package com.example.testauthservice.service;

import com.example.testauthservice.dto.UserDto;
import com.example.testauthservice.entity.UserEntity;
import com.example.testauthservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public @ResponseBody List<UserDto> getAllUser(){
        List<UserEntity> listUser = userRepository.findAll();

        return listUser.stream().map(User -> {
            return new UserDto(User);
        }).toList();

    }

    @Transactional
    @Override
    public UserEntity createUser(UserEntity user){
        log.debug("Creating user: {}", user.getUsername());
        log.debug("User details before save: {}", user);

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles("USER");
        }

        user.setCreatedAt(new Date());

        try{
            log.debug("Attempting to register user: {}", user.getUsername());

            userRepository.save(user);
            log.info("User registered successfully: {}", user.getUsername());
            log.debug("User details after save: {}", user);
        } catch (DataIntegrityViolationException e){
            log.error("Error registering user", e);
            throw new RuntimeException("Error registering user", e);
        }
        return user;
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username){
        log.debug("Attempting to retrieve user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id){
        log.debug("Attempting to retrieve user by ID: {}", id);
        return userRepository.findById(id);
    }



    @Override
    public UserDto getUserDto(long id) {
        Optional<UserEntity> mayBeUser = userRepository.findById(id);

        if (mayBeUser.isPresent()) {
            UserEntity user = mayBeUser.get();
            return new UserDto(user); // Assuming UserDto has a constructor that accepts UserEntity
        } else {
            return null; // Or throw an exception or handle the empty case according to your design
        }
    }

    @Override
    public boolean isUsernameTaken(String username){
        log.debug("Checking if username is taken: {}", username);
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    @Override
    public boolean isEmailTaken(String email){
        log.debug("Checking if email is taken: {}", email);
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.isPresent();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserEntity userEntity = userEntityOptional.get();

        // Create a GrantedAuthority from the role
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRoles()));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        //userEntity.getUsername(),
                        userEntity.getId().toString(),
                        userEntity.getPassword(),
                        authorities  // pass the authorities here
                );

        return userDetails;
    }


}
