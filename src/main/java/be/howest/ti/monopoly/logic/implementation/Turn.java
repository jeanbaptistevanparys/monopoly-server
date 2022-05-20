package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turn {
    private final Player player;
    private final List<Integer> roll;
    private final List<Move> moves;
    private final Tile nextTile;

    public Turn(Player player, int dice1, int dice2) {
        this.player = player;
        this.moves = new ArrayList<>();
        this.roll = new ArrayList<>(List.of(dice1, dice2));
        this.nextTile = Helper.getNextTile(player.getCurrentTile(), roll.get(0) + roll.get(1));
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void executeTurn(Game game) {
        if (player.isJailed()) {
            inJailTurn(nextTile);
        } else if (Helper.isStreet(nextTile)) {
            streetTurn(nextTile, game);
        } else if (Helper.isCard(nextTile)) {
            cardTurn(nextTile);
        } else if (Helper.isTax(nextTile)) {
            taxTurn(nextTile);
        } else if (Helper.isRailRoad(nextTile)) {
            railRoadTurn(nextTile);
        } else if (Helper.isUtility(nextTile)) {
            utilityTurn(nextTile);
        } else if (Helper.isGoToJail(nextTile)) {
            goToJailTurn(nextTile);
        } else if (Helper.isGo(nextTile)) {
            goTurn();
        } else {
            freeParkingTurn(nextTile);
        }
    }

    private void inJailTurn(Tile nextTile) {
        String description = "Out of jail";
        if (isDouble()) {
            player.getOutOfJailDouble();
        } else if (player.getTriesToGetOutOfJail() == 3) {
            player.getOutOfJailFine();
        } else {
            player.addTrieToGetOutOfJail();
            nextTile = Helper.getTile("Repair");
            description = "Stay in jail";
        }
        Move move = new Move(nextTile, description);
        move.executeMove(this);
        addMove(move);
    }

    private void streetTurn(Tile nextTile, Game game) {
        String description = "";
        if (Helper.isDirectSale(nextTile, game.getPlayers())) {
            description = "Direct sale";
            game.setCanRoll(false);
        } else {
            description = "Should pay rent";
        }
        player.moveTile(nextTile.getName());
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

    private void goToJailTurn(Tile nextTile) {
        player.goToJail();
        Move move = new Move(nextTile, "Go to jail");
        move.executeMove(this);
        addMove(move);
    }

    private void goTurn() {
        player.receiveMoney(200);
        Move move = new Move(nextTile, "Go (Collect 200)");
        move.executeMove(this);
        addMove(move);
    }

    private void freeParkingTurn(Tile nextTile) {
        Move move = new Move(nextTile, "Free parking");
        move.executeMove(this);
        addMove(move);
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isDouble() {
        return Objects.equals(roll.get(0), roll.get(1));
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Tile getNextTile() {
        return nextTile;
    }
}
