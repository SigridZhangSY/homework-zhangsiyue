package rich.commandHandler;

import rich.Player;

import java.util.Optional;

public class WaitBuyResponseHandler implements CommandHandler {
    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        if(input.equalsIgnoreCase("y"))
            player.buyEmpty();
        return Optional.empty();
    }
}
