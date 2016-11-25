package rich;

import rich.commandHandler.CommandHandler;
import rich.place.Place;

public class Player {

    private CommandHandler handler;

    private Place currentPlace;


    public Player(CommandHandler handler) {
        this.handler = handler;
    }

    public Player(CommandHandler handler, Place currentPlace) {
        this.handler = handler;
        this.currentPlace = currentPlace;
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

    public Place getCurrentPlace() {
        return currentPlace;
    }
}
