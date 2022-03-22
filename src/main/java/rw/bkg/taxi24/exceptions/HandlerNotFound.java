package rw.bkg.taxi24.exceptions;

public class HandlerNotFound extends RuntimeException {
    public HandlerNotFound(String message) {
        super(message);
    }
    public HandlerNotFound(Throwable cause) {
        super(cause);
    }
    public HandlerNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
