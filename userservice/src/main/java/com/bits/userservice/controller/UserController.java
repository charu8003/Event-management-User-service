package com.bits.userservice.controller;
import com.bits.userservice.model.User;

import com.bits.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public User registerUser(@RequestBody User user){

        return userService.registerUser(user);

    }
}
