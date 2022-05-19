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
        for (int i = game.getTile(currentPlayer.getCurrentTile()).getPosition(); i <= game.getTiles().size(); i++) {
            if (i == 40) i = 0;
            Tile tile = game.getTiles().get(i);
            if (tile.getName().equals(tileName)) {
                if (game.passedGo(tile.getName(), currentPlayer.getCurrentTile())) {
                    currentPlayer.receiveMoney(200);
                    turn.addMove(new Move(game.getTile("Boot"), "Passed Boot (receive $200)"));
                }
                currentPlayer.moveTile(tile.getName());
                turn.addMove(new Move(tile, "Moved to tile " + tileName));
                break;
            }
        }
    }
}
