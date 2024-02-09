package com.findyourrent.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findyourrent.api.entity.UserEntity;
import com.findyourrent.api.repository.UserRepository;
import com.findyourrent.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
