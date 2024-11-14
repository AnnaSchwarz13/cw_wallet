package exceptions;

public class WalletExceptions extends Exception {
    public WalletExceptions() {}

    public WalletExceptions(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
