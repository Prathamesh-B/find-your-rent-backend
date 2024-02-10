package com.findyourrent.api.service;

import java.util.List;

import com.findyourrent.api.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    UserEntity getUserByEmail(String email);

    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    UserEntity validateUser(String username, String password);

    void deleteUser(Long id);

}
