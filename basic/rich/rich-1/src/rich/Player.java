package rich;

import rich.commandHandler.CommandHandler;
import rich.place.Estate;
import rich.place.Place;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private CommandHandler handler;

    private Place currentPlace;

    private double balance;

    private List<Estate> estates;

    private boolean endGame;

    public Player(CommandHandler handler) {
        this.handler = handler;
    }

    public Player(CommandHandler handler, Place currentPlace) {
        this.handler = handler;
        this.currentPlace = currentPlace;
    }

    public Player(CommandHandler handler, Place currentPlace, double balance) {
        this.handler = handler;
        this.currentPlace = currentPlace;
        this.balance = balance;
        estates = new ArrayList<>();
        endGame = false;
    }

    public void executed(String input){
        handler = handler.execute(input, this).orElse(null);
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public void moveTo(Map map, Dice dice){
        currentPlace = map.move(currentPlace, dice.next());
    }

    public void buyEmpty(){
        Estate empty = (Estate) this.currentPlace;
        if(balance >= empty.getPrice()){
            empty.buy(this);
            balance -= empty.getPrice();
            estates.add(empty);
        }
    }

    public void buildEstate(){
        Estate estate = (Estate)currentPlace;
        if(balance >= estate.getPrice() && estate.build())
            balance -= estate.getPrice();
    }

    public void payFee(double fee, Player owner) {
        if(balance >= fee) {
            balance -= fee;
            owner.getFee(fee);
        }
        else {
            estates.stream().forEach(estate -> estate.backToGame());
            endGame = true;
        }
    }

    public void getFee(double fee){
        balance += fee;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }


    public double getBalance() {
        return balance;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public boolean isEndGame() {
        return endGame;
    }
}
