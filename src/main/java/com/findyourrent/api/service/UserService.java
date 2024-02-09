package com.findyourrent.api.service;

import java.util.List;

import com.findyourrent.api.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    UserEntity getUserByEmail(String email);

    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    void deleteUser(Long id);
    // UserEntity authenticateUser(String username, String password);
    // UserEntity registerUser(String username, String email, String password);
    // UserEntity updateUserPassword(String username, String oldPassword, String
    // newPassword);
    // UserEntity updateUserEmail(String username, String email);

}
