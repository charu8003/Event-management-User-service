package com.bits.userservice.service;

import com.bits.userservice.exception.UserDataValidationException;
import com.bits.userservice.model.User;
import com.bits.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Autowire RestTemplate to make the HTTP call
    @Autowired
    private RestTemplate restTemplate;

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


        sendNotification(savedUser.getId());

        return savedUser;
    }

    private void sendNotification(String userId) {
        try {

            String notificationServiceUrl = "https://jsonplaceholder.typicode.com/posts";  // Example URL

            String url = notificationServiceUrl + "?userId=" + userId;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Email sent successfully for user ID: " + userId);
            } else {
                System.out.println("Failed to send email for user ID: " + userId);
            }
        } catch (Exception e) {

            System.err.println("Error while sending notification: " + e.getMessage());
        }
    }
}
