package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitToolResponseHandler;

import java.util.Optional;

public class ToolHouse implements Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        if (player.getTools().size() == 10 || player.getPoints() < 30)
            return Optional.empty();
        return Optional.of(new WaitToolResponseHandler());
    }
}
