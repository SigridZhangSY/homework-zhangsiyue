package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;

import java.util.Optional;

public class Hospital implements Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        return Optional.empty();
    }
}
