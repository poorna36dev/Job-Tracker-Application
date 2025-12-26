package com.poorna.JobTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/id")
    public String getId() {
        return "User";
    }


}
