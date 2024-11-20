package com.bits.userservice.exception;

public class UserDataValidationException extends RuntimeException {
    public UserDataValidationException(String message) {
        super(message);
    }
}
