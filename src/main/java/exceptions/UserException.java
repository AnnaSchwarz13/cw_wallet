package exceptions;

public class UserException extends Exception {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
