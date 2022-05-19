package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;

public class MoveToNearestCard extends Card {

    private final String nearestType;

    public MoveToNearestCard(String description, String nearestType) {
        super(description);
        this.nearestType = nearestType;
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
        
    }

    public String getNearestType() {
        return nearestType;
    }
}
