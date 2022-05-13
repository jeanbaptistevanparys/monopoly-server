package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Map;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public List<Tile> getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int position) {
        return delegate.getTile(position);
    }

    @Override
    public Tile getTile(String name) {
        return delegate.getTile(name);
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        return delegate.getGames(started, numberOfPlayers, prefix);
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        return delegate.createGames(prefix, numberOfPlayers);
    }

    @Override
    public Object getChance() {
        return delegate.getChance();
    }

    @Override
    public Object getCommunityChest() {
        return delegate.getCommunityChest();
    }

    @Override
    public List<Auction> getBankAuctions() {
        return delegate.getBankAuctions();
    }

    @Override
    public Object placeBidOnBankAuction() {
        return delegate.placeBidOnBankAuction();
    }

    @Override
    public List<Auction> getPlayerAuctions() {
        return delegate.getPlayerAuctions();
    }

    @Override
    public Object startPlayerAuction() {
        return delegate.startPlayerAuction();
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        return delegate.placeBidOnPlayerAuction();
    }

    @Override
    public Game getGame(String gameId) {
        return delegate.getGame(gameId);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }

    public Object rollDice(String gameId, String playerName) {
        return delegate.rollDice(gameId, playerName);
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        return delegate.declareBankruptcy(gameId, playerName);
    }

    public Object joinGame(String playerName, String gameId) {
        return delegate.joinGame(playerName, gameId);
    }

    @Override
    public Object clearGameList() {
        return delegate.clearGameList();
    }

    @Override
    public Object getOutOfJailFine() {
        return delegate.getOutOfJailFine();
    }

    @Override
    public Object getOutOfJailFree() {
        return delegate.getOutOfJailFree();
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        return delegate.buyProperty(gameId, playerName, propertyName);
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        return delegate.dontBuyProperty(gameId, playerName, propertyName);
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName) {
        return delegate.buyHouse(gameId, playerName, propertyName);
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        return delegate.sellHouse(gameId, playerName, propertyName);
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        return delegate.buyHotel(gameId, playerName, propertyName);
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        return delegate.sellHotel(gameId, playerName, propertyName);
    }

    @Override
    public Object useEstimateTax(String gameId, String playerName) {
        return delegate.useEstimateTax(gameId, playerName);
    }

    @Override
    public Object useComputeTax(String gameId, String playerName) {
        return delegate.useComputeTax(gameId, playerName);
    }

    public Object takeMortgage() {
        return delegate.takeMortgage();
    }

    @Override
    public Object settleMortgage() {
        return delegate.settleMortgage();
    }
}
