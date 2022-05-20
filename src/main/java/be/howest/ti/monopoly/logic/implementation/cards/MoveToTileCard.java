package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class MoveToTileCard extends Card {

    private final String tileName;

    public MoveToTileCard(String description, String tileName) {
        super(description);
        this.tileName = tileName;
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
        Tile nextTile = game.getTile(tileName);
        currentPlayer.receiveMoney(200);
        turn.addMove(new Move(game.getTile("Boot"), "Passed Boot (receive $200)"));
        currentPlayer.moveTile(nextTile.getName());
        turn.addMove(new Move(nextTile, "Moved to " + tileName));
    }
}
