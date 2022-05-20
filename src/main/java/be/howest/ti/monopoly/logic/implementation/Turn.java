package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turn {
    private final String player;
    private final List<Integer> roll;
    private final List<Move> moves;

    public Turn(String player, int dice1, int dice2) {
        this.player = player;
        this.moves = new ArrayList<>();
        this.roll = new ArrayList<>(List.of(dice1, dice2));
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public String getPlayer() {
        return player;
    }

    public boolean isDouble() {
        return Objects.equals(roll.get(0), roll.get(1));
    }

    public List<Move> getMoves() {
        return moves;
    }
}
