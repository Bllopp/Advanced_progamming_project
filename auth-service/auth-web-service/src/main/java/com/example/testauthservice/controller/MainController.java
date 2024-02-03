package com.example.testauthservice.controller;

import com.example.testauthservice.config.JwtTokenProvider;
import com.example.testauthservice.constants.UrlConstants;
import com.example.testauthservice.dto.UserRegistrationDto;
import com.example.testauthservice.entity.UserEntity;
import com.example.testauthservice.service.UserService;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequestMapping(path = UrlConstants.HOME_URL)
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /*@PostMapping(path = UrlConstants.LOGIN_URL)
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserRegistrationDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);

        } catch (ArithmeticException e) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            log.error("Error during login", e);
            return new ResponseEntity<>("Unexpected error during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PostMapping(path=UrlConstants.REGISTER_URL)
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDto userDto) {
        try {
            log.info("Entering registerUser method");
            if (userService.isUsernameTaken(userDto.getUsername()) || userService.isEmailTaken(userDto.getEmail())) {
                return new ResponseEntity<>("Username or email already taken", HttpStatus.BAD_REQUEST);
            }

            String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
            userDto.setPassword(hashedPassword);

            UserEntity newUser = convertDtoToEntity(userDto);
            UserEntity savedUser = userService.createUser(newUser);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userDto.getRole()));

            if (savedUser != null) {
                String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(savedUser.getUsername(), null, authorities));
                log.info("Exiting registerUser method");
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error registering user", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            log.error("Error registering user", e);
            return new ResponseEntity<>("Unexpected error during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private UserEntity convertDtoToEntity(UserRegistrationDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword()); //hash the password
        userEntity.setEmail(userDto.getEmail());
        userEntity.setRoles(userDto.getRole());
        return userEntity;
    }
}