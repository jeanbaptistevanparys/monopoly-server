package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class MoveToNearestCard extends Card {

    private final String nearestType;

    public MoveToNearestCard(String description, String nearestType) {
        super(description);
        this.nearestType = nearestType;
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
        for (int i = game.getTile(currentPlayer.getCurrentTile()).getPosition(); i <= game.getTiles().size(); i++) {
            Tile tile = game.getTiles().get(i);
            if (game.getTile(currentPlayer.getCurrentTile()).getPosition() == game.getTiles().size()) i = 0;
            if (tile.getType().equals(nearestType)) {
                if (game.passedGo(tile.getName(), currentPlayer.getCurrentTile())) {
                    currentPlayer.receiveMoney(200);
                    turn.addMove(new Move(game.getTile("Boot"), "Passed Boot (receive $200)"));
                }
                currentPlayer.moveTile(tile.getName());
                turn.addMove(new Move(tile, "Moved to nearest " + nearestType));
                break;
            }
        }
    }
}
