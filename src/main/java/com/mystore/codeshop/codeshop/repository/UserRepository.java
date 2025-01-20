package com.mystore.codeshop.codeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.codeshop.codeshop.entity.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends JpaRepository<User, Long> {

}

