package com.trading.exceptions;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message, Throwable error) {
        super(message, error);
    }

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException() {
        super("Requested stock could not be retrieved");
    }
}
