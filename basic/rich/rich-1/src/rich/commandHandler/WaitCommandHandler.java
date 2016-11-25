package rich.commandHandler;

import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.Estate;

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
        if(input.equalsIgnoreCase("roll"))
            player.moveTo(map, dice);
        if(player.getCurrentPlace() instanceof Estate) {
            Player owner = ((Estate) player.getCurrentPlace()).getOwner();
            if ( owner == null || owner.equals(player))
                return Optional.of(new WaitResponseHandler());
        }
        return Optional.empty();
    }
}
