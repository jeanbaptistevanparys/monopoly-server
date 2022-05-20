package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turn {
    private final Player player;
    private final List<Integer> roll;
    private final List<Move> moves;

    public Turn(Player player, int dice1, int dice2) {
        this.player = player;
        this.moves = new ArrayList<>();
        this.roll = new ArrayList<>(List.of(dice1, dice2));
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void executeTurn(Game game) {
        Tile nextTile = Helper.getNextTile(player.getCurrentTile(), roll.get(0) + roll.get(1));
        if (Helper.isStreet(nextTile)) {
            streetTurn(nextTile, game);
        } else if (Helper.isCard(nextTile)) {
            cardTurn(nextTile);
        } else if (Helper.isTax(nextTile)) {
            taxTurn(nextTile);
        } else if (Helper.isRailRoad(nextTile)) {
            railRoadTurn(nextTile);
        } else if (Helper.isUtility(nextTile)) {
            utilityTurn(nextTile);
        } else {
            addMove(new Move(nextTile, ""));
        }
    }

    private void streetTurn(Tile nextTile, Game game) {
        String description = "";
        if (Helper.isDirectSale(nextTile, game.getPlayers())) {
            description = "Direct sale";
            game.setCanRoll(false);
        } else {
            description = "Should pay rent";
        }
        addMove(new Move(nextTile, description));
    }

    private void cardTurn(Tile nextTile) {
        addMove(new Move(nextTile, ""));
    }

    private void taxTurn(Tile nextTile) {
        addMove(new Move(nextTile, ""));
    }

    private void railRoadTurn(Tile nextTile) {
        addMove(new Move(nextTile, ""));
    }

    private void utilityTurn(Tile nextTile) {
        addMove(new Move(nextTile, ""));
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public String getPlayer() {
        return player.getName();
    }

    public boolean isDouble() {
        return Objects.equals(roll.get(0), roll.get(1));
    }

    public List<Move> getMoves() {
        return moves;
    }
}
