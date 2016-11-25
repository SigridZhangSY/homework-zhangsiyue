package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitMagicResponse;
import rich.commandHandler.WaitToolResponse;

import java.util.Optional;

public class MagicHouse implements Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        return Optional.of(new WaitMagicResponse());
    }
}
