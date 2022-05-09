package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;


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
}
