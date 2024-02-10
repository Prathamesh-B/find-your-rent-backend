package com.findyourrent.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findyourrent.api.entity.UserEntity;
import com.findyourrent.api.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserEntity loginUser = userService.validateUser(email, password);

        if (loginUser != null) {
            return new ResponseEntity<>(generateResponse(true, generateJWTToken(loginUser)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(generateResponse(false, null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody UserEntity user) {
        UserEntity registeredUser = userService.createUser(user);
        return new ResponseEntity<>(generateResponse(true, generateJWTToken(registeredUser)), HttpStatus.OK);
    }

    private Map<String, Object> generateResponse(boolean success, Map<String, String> authToken) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (authToken != null) {
            response.put("authtoken", authToken.get("token"));
        }
        return response;
    }

    private Map<String, String> generateJWTToken(UserEntity user) {
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
