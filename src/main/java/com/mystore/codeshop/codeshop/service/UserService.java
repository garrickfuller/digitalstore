package com.mystore.codeshop.codeshop.service;

import com.mystore.codeshop.codeshop.entity.User;
import com.mystore.codeshop.codeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new user and save to the database.
     */
    public User registerUser(User user) {
        // later i need to check if user already exists and encrypt password
        return userRepository.save(user);
    }

    /**
     * Find all users in the database.
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find a single user by its ID.
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Update an existing user’s data.
     * 
     * 1. Look up ID to make sure it exists
     * 2. If found, update the fields
     * 3. Save it back to the repository and return it.
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // later going to pick which fields from updatedUser you want to allow updates to
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setPassword(updatedUser.getPassword());
                    
                    // existingUser.setEmail(updatedUser.getEmail());
                    // existingUser.setRole(updatedUser.getRole());

                    userRepository.save(existingUser); // save changes
                    return existingUser;
                });
    }

    /**
     * Delete a user by ID.
     * 
     * 1. Check if user exists
     * 2. If so, delete and return true
     * 3. Otherwise, return false if user is not found or other fails
     */
    public boolean deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
            return true;
        }
        return false;
    }
}
