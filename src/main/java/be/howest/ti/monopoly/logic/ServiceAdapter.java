package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

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
    public Object getChance() {
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
    public Object getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Auction> getBankAuctions() {
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
    public Object placeBidOnBankAuction() {
        throw new UnsupportedOperationException();
    }

    public Object getOutOfJailFine() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Auction> getPlayerAuctions() {
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
    public Object startPlayerAuction() {
        throw new UnsupportedOperationException();
    }


    public Object getOutOfJailFree() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object useEstimateTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName){
        throw new UnsupportedOperationException();
    }

    @Override
    public Object takeMortgage() {
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
    public Object settleMortgage() {
        throw new UnsupportedOperationException();
    }

}
