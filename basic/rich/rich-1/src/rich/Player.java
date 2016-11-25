package rich;

public class Player {

    private CommandHandler handler;


    public Player(CommandHandler handler) {
        this.handler = handler;
    }

    public void executed(String input){
        handler = handler.execute(input, this).orElse(null);
    }

    public CommandHandler getHandler() {
        return handler;
    }

}
