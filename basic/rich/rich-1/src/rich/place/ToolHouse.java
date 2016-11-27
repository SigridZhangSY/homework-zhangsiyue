package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitToolResponseHandler;

import java.util.Optional;

public class ToolHouse implements Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        return Optional.of(new WaitToolResponseHandler());
    }
}
