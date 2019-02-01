package utils;

public class Exceptions extends Exception {

    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(Throwable cause) {
        super (cause);
    }

    public Exceptions(String message, Throwable cause) {
        super (message, cause);
    }

}