package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.cards.ChangeMoneyCard;
import be.howest.ti.monopoly.logic.implementation.cards.OutOfJailCard;
import be.howest.ti.monopoly.logic.implementation.factories.CardFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turn {
    private final Player player;
    private final List<Integer> roll;
    private final List<Move> moves;
    private Tile nextTile;
    private Game game;

    public Turn(Player player, int dice1, int dice2) {
        this.player = player;
        this.moves = new ArrayList<>();
        this.roll = new ArrayList<>(List.of(dice1, dice2));
        this.nextTile = Helper.getNextTile(player.getCurrentTile(), roll.get(0) + roll.get(1));
        this.game = null;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void executeTurn(Game game) {
        this.game = game;
        if (Helper.passedGo(nextTile.getName(), player.getCurrentTile()) && !player.isJailed()) {
            player.receiveMoney(200);
            Move move = new Move(Helper.getTile("Boot"), "Passed Boot (Collect 200)");
            if (nextTile.getType().equals("Go")) move.executeMove(this);
            addMove(move);
        }
        if (player.isJailed()) {
            inJailTurn(nextTile);
        } else if (Helper.isStreet(nextTile)) {
            streetTurn(nextTile);
        } else if (Helper.isCard(nextTile)) {
            cardTurn(nextTile);
        } else if (Helper.isTax(nextTile)) {
            taxTurn(nextTile);
        } else if (Helper.isRailRoad(nextTile)) {
            railRoadTurn(nextTile);
        } else if (Helper.isUtility(nextTile)) {
            utilityTurn(nextTile);
        } else if (Helper.isGoToJail(nextTile)) {
            goToJailTurn();
        } else if (!Helper.isGo(nextTile)) {
            Move move = new Move(nextTile, "");
            if (nextTile.getType().equals("Free Parking")) move.setDescription(nextTile.getName());
            else if (nextTile.getType().equals("Jail")) move.setDescription("Just visiting");
            move.executeMove(this);
            addMove(move);
        }
    }

    public void executeTurn(Tile nextTile) {
        this.nextTile = nextTile;
        executeTurn(game);
    }

    private void inJailTurn(Tile nextTile) {
        if (isDouble()) {
            player.getOutOfJailDouble();
            executeTurn(nextTile);
        } else if (player.getTriesToGetOutOfJail() == 3) {
            player.getOutOfJailFine();
            executeTurn(nextTile);
        } else {
            player.addTrieToGetOutOfJail();
            nextTile = Helper.getTile("Repair");
            Move move = new Move(nextTile, "Stay in Repair");
            move.executeMove(this);
            addMove(move);
        }
    }

    private void streetTurn(Tile nextTile) {
        String description = "";
        if (Helper.isDirectSale(nextTile, game.getPlayers())) {
            description = "Direct sale";
            game.setCanRoll(false);
        } else {
            description = "Should pay rent";
        }
        player.moveTile(nextTile.getName());
        Move move = new Move(nextTile, description);
        move.executeMove(this);
        addMove(move);
    }

    private void cardTurn(Tile nextTile) {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(14);
        Card card;
        if (nextTile.getType().equals("chance")) card = new CardFactory().createChances().get(number);
        else card = new CardFactory().createCommunityChests().get(number);
        Move move = new Move(nextTile, card.getDescription());
        move.executeMove(this);
        addMove(move);
        card.executeCard(player, this);
    }

    private void taxTurn(Tile nextTile) {
        Move move = new Move(nextTile, "");
        move.executeMove(this);
        addMove(move);
    }

    private void railRoadTurn(Tile nextTile) {
        Move move = new Move(nextTile, "");
        move.executeMove(this);
        addMove(move);
    }

    private void utilityTurn(Tile nextTile) {
        Move move = new Move(nextTile, "");
        move.executeMove(this);
        addMove(move);
    }

    private void goToJailTurn() {
        player.goToJail();
        Move move = new Move(Helper.getTile("Repair"), "Go to Repair");
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
