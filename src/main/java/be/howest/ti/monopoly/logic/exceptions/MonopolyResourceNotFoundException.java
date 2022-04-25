package be.howest.ti.monopoly.logic.exceptions;

public class MonopolyResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MonopolyResourceNotFoundException(String msg) {
        super(msg);
    }
}
