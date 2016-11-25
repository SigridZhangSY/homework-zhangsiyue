package rich.commandHandler;

import rich.Dice;
import rich.Map;
import rich.Player;

import java.util.Optional;

public class WaitCommandHandler implements CommandHandler{
    private Map map;
    private Dice dice;

    public WaitCommandHandler(Map map, Dice dice) {
        this.map = map;
        this.dice = dice;
    }


    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        if(input.equalsIgnoreCase("roll")) {
            player.moveTo(map, dice);
            return player.getCurrentPlace().nextCommandHandler(player);
        }
        return Optional.empty();
    }
}
