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

    public Tile executeTurn() {
        Tile nextTile = Helper.getNextTile(player.getCurrentTile(), roll.get(0) + roll.get(1));
        if (Helper.isStreet(nextTile)) {
            streetTurn();
        } else if (Helper.isCard(nextTile)) {
            cardTurn();
        } else if (Helper.isTax(nextTile)) {
            taxTurn();
        } else if (Helper.isRailRoad(nextTile)) {
            railRoadTurn();
        } else if (Helper.isUtility(nextTile)) {
            utilityTurn();
        } else {
            addMove(new Move(nextTile, "Nothing to do"));
        }
        return nextTile;
    }

    private void streetTurn() {
    }

    private void cardTurn() {
    }

    private void taxTurn() {
    }

    private void railRoadTurn() {
    }

    private void utilityTurn() {
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
