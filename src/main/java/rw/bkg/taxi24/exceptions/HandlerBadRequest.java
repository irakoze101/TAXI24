package rw.bkg.taxi24.exceptions;

public class HandlerBadRequest extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public HandlerBadRequest(String message) {
        super(message);
    }
    public HandlerBadRequest(Throwable cause) {
        super(cause);
    }
    public HandlerBadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}
