package utils;

/**
 * Exception class
 *
 * @author Pinchuk Dmitry
 */
public class Exceptions extends RuntimeException {

    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(Throwable cause) {
        super(cause);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }

}