package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
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

    @Override
    public Object getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Auction> getBankAuctions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnBankAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Auction> getPlayerAuctions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object startPlayerAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        throw new UnsupportedOperationException();
    }

}
