package com.bits.userservice.service;

import com.bits.userservice.exception.UserDataValidationException;
import com.bits.userservice.model.User;
import com.bits.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public User registerUser(User user) {

        if(user.getMobile() == null && user.getEmail() == null) {
            throw new UserDataValidationException("(Email or mobile) field is mandatory");
        }
        user.setInsertDate(new Date());
        user.setId(UUID.randomUUID().toString());
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
