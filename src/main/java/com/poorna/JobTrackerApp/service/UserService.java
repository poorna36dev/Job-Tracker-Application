package com.poorna.JobTrackerApp.service;


import org.springframework.stereotype.Service;

import com.poorna.JobTrackerApp.dtos.UserRequest;
import com.poorna.JobTrackerApp.entity.User;
import com.poorna.JobTrackerApp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest userRequest) {
        User user= new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setActive(userRequest.getIsActive());
        userRepository.save(user);
    }
}
