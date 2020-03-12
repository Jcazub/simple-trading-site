package com.trading.exceptions;

public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException(String message, Throwable error) {
        super(message, error);
    }

    public NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException() {
        super("You do not have enough funds for this purchase.");
    }
}
