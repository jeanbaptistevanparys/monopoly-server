package be.howest.ti.monopoly.web.exceptions;

public class NotYetImplementedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotYetImplementedException(String endpoint) {
        super("This endpoint has not yet been implemented: " + endpoint);
    }
}
