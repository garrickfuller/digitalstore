package com.mystore.codeshop.codeshop.service;

import com.mystore.codeshop.codeshop.entity.User;
import com.mystore.codeshop.codeshop.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // Constructor Injection for these beans
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user and save to the database.
     */
    public User registerUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with this username already exists.");
        }

        //hash the password that the user creates and then resave as encrypted
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // only store the hash in DB
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
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        // making sure password is hashed before updating
                        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    //not really sure if this even makes it faster/better but keeping if statements here for time being
                    if (existingUser.getUsername() != null) {
                        
                        existingUser.setUsername(updatedUser.getUsername());

                    }
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
