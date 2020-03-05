package com.trading.exceptions;

public class MalformedObjectException extends Exception {

    public MalformedObjectException(String message, Throwable error){
        super(message, error);
    }

    public MalformedObjectException(String message) {
        super(message);
    }

    public MalformedObjectException() {
        super("Passed object was malformed, requested operation could not be performed");
    }
}
