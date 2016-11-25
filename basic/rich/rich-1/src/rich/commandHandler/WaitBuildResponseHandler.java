package rich.commandHandler;

import rich.Player;

import java.util.Optional;

public class WaitBuildResponseHandler implements CommandHandler {
    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        if(input.equalsIgnoreCase("y"))
            player.buildEstate();
        return Optional.empty();
    }
}
