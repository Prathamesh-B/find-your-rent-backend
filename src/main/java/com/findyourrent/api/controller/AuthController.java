package com.findyourrent.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findyourrent.api.entity.UserEntity;
import com.findyourrent.api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@RequestBody UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserEntity loginUser = userService.validateUser(email, password);

        if (loginUser != null) {
            // Password is correct
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        UserEntity registeredUser = userService.createUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
