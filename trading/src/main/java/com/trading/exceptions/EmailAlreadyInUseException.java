package com.trading.exceptions;

public class EmailAlreadyInUseException extends Exception {

    public EmailAlreadyInUseException(String message, Throwable error) {
        super(message, error);
    }

    public EmailAlreadyInUseException(String message) {
        super(message);
    }

    public EmailAlreadyInUseException() {
        super("This email is already registered with a user");
    }
}
