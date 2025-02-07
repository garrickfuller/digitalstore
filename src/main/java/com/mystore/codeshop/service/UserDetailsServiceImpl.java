package com.mystore.codeshop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsServiceImpl {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
