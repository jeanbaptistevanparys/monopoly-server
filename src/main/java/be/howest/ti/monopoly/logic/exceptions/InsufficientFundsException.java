package be.howest.ti.monopoly.logic.exceptions;

public class InsufficientFundsException extends IllegalMonopolyActionException {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
