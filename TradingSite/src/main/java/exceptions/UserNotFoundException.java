package exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message, Throwable error) {
        super(message, error);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Requested transaction could not be retrieved");
    }

}
