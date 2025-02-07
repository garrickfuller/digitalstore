package com.mystore.codeshop.security.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mystore.codeshop.repository.UserRepository;
import com.mystore.codeshop.entity.User;

/**
 * A Spring Security service that loads a UserDetails 
 * by a given username from the DB.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return UserDetailsImpl.build(user); // Build UserDetailsImpl from User
  }
}
