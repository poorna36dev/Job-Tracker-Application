package com.poorna.JobTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poorna.JobTrackerApp.dtos.UserRequest;
import com.poorna.JobTrackerApp.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public String createUser(@RequestBody UserRequest userRequest) {

        userService.createUser(userRequest);
        return "user created successfully";
    }
    
}
