package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;

public abstract class Card {

    private final String description;

    protected Card(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void executeCard(Player currentPlayer, Turn turn) {
    }
}
