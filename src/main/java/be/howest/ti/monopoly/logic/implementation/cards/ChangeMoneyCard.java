package be.howest.ti.monopoly.logic.implementation.cards;

public class ChangeMoneyCard extends Card {

    private int changeBalance;

    public ChangeMoneyCard(String description, int changeBalance) {
        super(description);
        this.changeBalance = changeBalance;
    }
}
