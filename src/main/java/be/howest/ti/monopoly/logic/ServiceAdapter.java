package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getChance() {
        throw new UnsupportedOperationException();
    }

    public List<Game> getGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Auction> getBankAuctions(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Game> getGames( boolean started, int numberOfPlayers, String prefix){
        throw new UnsupportedOperationException();
    }

    @Override
    public Object rollDice(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object joinGame(String playerName, String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnBankAuction(String gameId, String propertyName, String bidder, int amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFine(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clearGameList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFree(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object trade(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object useEstimateTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object takeMortgage(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName){
        throw new UnsupportedOperationException();
    }

    @Override
    public Object useComputeTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object settleMortgage(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

}
