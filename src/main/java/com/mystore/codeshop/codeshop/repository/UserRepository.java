package com.mystore.codeshop.codeshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.mystore.codeshop.codeshop.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository


public interface UserRepository extends CrudRepository<User, Integer> {

}

