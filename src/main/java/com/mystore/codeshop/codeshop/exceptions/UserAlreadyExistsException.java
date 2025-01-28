package com.mystore.codeshop.codeshop.exceptions;



public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
