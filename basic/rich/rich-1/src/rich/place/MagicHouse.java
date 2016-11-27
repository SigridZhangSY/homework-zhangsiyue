package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitMagicResponse;

import java.util.Optional;

public class MagicHouse extends Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        return Optional.of(new WaitMagicResponse());
    }
}
