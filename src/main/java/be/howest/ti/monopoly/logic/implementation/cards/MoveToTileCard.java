package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;

public class MoveToTileCard extends Card{

    private final String tileName;

    public MoveToTileCard(String description, String tileName) {
        super(description);
        this.tileName = tileName;
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
    }

    public String getTileName() {
        return tileName;
    }
}
