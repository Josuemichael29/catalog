package com.cdis.microservice.example.auth.service;

import com.cdis.microservice.example.auth.model.User;

import java.util.List;

public interface UserService {
    // Add
    User addUser(User user);

    // Find
    List<User> findUsers();
    User findUserByUsername(String username);
    User findUserById(Long id);

    // Update
    void updateUser(User user);

    // Delete
    void deleteUsernameByUsername(String username);
    void deleteUsernameById(Long id);

    // Send User Credentials
    String sendUserCredentials(String username);
}
