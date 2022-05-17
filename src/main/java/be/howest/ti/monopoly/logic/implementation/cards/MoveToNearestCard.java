package be.howest.ti.monopoly.logic.implementation.cards;

public class MoveToNearestCard extends Card {

    private final String nearestType;

    public MoveToNearestCard(String description, String nearestType) {
        super(description);
        this.nearestType = nearestType;
    }
}
