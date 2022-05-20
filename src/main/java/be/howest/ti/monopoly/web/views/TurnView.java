package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Turn;

import java.util.List;

public class TurnView {

    private final Turn turn;

    public TurnView(Turn turn) {
        this.turn = turn;
    }

    public List<Integer> getRoll() {
        return turn.getRoll();
    }

    public String getPlayer() {
        return turn.getPlayer().getName();
    }

    public boolean isDouble() {
        return turn.isDouble();
    }

    public List<Move> getMoves() {
        return turn.getMoves();
    }

}
