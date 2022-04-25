package be.howest.ti.monopoly.logic.exceptions;

public class IllegalMonopolyActionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalMonopolyActionException(String message) {
        super(message);
    }
}
