package rich;

import rich.commandHandler.CommandHandler;
import rich.place.Estate;
import rich.place.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private CommandHandler handler;

    private Place currentPlace;

    private double balance;

    private List<Estate> estates;

    private boolean endGame;

    private int points;

    private int freeTimes;

    private List<Tool> tools;

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
        points = 0;
        freeTimes = -1;
        tools = new ArrayList<>();
    }

    public static Player createPlayerWithPoints(CommandHandler handler, Place currentPlace, double balance, int points){
        Player player = new Player(handler, currentPlace, balance);
        player.points = points;
        return player;
    }

    public void executed(String input){
        handler = handler.execute(input, this).orElse(null);
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public void moveTo(Map map, Dice dice){
        currentPlace = map.move(currentPlace, dice.next());
        if(currentPlace instanceof Estate)
            ((Estate) currentPlace).arrive(this);
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

    public void sellEstate(Estate estate){
        estate.backToGame();
        balance += estate.getPrice() * (estate.getLevel().ordinal() + 1) * 2;
        estates.remove(estate);
    }

    public void selectGift(int n){
        switch (n){
            case 1:
                balance += 2000;
                break;
            case 2:
                points += 200;
                break;
            case 3:
                freeTimes = 5;
                break;
            default:
                return;
        }
    }

    public void buyTool(int choice) {
        Tool tool = new Tool(Tool.ToolType.values()[choice]);
        tools.add(tool);
        points -= tool.getPointPrice();
    }

    public void sellTool(int choice) {
        Tool.ToolType type = Tool.ToolType.values()[choice];
        Optional<Tool> tool = tools.stream().filter(t -> t.getType() == type).findFirst();
        if(tool.isPresent()){
            tools.remove(tool.get());
            points += tool.get().getPointPrice();
        }
    }

    public void userTool(Tool.ToolType type, Place place, Map map){
        Optional<Tool> tool = tools.stream().filter(t -> t.getType() == type).findFirst();
        if(tool.isPresent()){
            switch (type){
                case BLOCK:
                    if(place.setBlock())
                        tools.remove(tool.get());
                    break;
                case BOMB:
                    if(place.setBomb())
                        tools.remove(tool.get());
                    break;
                case ROBOT:
                    map.clearTool(currentPlace, 10);
                    tools.remove(tool.get());
                default:
                    return;
            }
        }
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

    public int getPoints() {
        return points;
    }

    public int getFreeTimes() {
        return freeTimes;
    }

    public List<Tool> getTools() {
        return tools;
    }

}
