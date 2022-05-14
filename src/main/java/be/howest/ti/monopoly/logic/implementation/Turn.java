package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private final String player;
    private final List<Integer> roll;

    public Turn(String player, int dice1, int dice2) {
        this.player = player;
        this.roll = new ArrayList<>(List.of(dice1, dice2));
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public String getName() {
        return player;
    }
}