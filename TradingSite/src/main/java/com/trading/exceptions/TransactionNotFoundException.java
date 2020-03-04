package com.trading.exceptions;

public class TransactionNotFoundException extends Exception {

    public TransactionNotFoundException(String message, Throwable error){
        super(message, error);
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException() {
        super("Requested transaction could not be retrieved");
    }
}
