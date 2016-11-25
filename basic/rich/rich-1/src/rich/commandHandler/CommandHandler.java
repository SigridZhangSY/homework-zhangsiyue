package rich.commandHandler;

import rich.Player;

import java.util.Optional;

public interface CommandHandler {
    Optional<CommandHandler> execute(String input, Player player);
}
