package com.mystore.codeshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    // Public endpoint accessible without authentication
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    // Endpoint accessible by users with ROLE_USER, or ROLE_ADMIN
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    

    // Endpoint accessible only by users with ROLE_ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
