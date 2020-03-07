package com.trading.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message, Throwable error) {
        super(message, error);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Requested user could not be retrieved");
    }

}
