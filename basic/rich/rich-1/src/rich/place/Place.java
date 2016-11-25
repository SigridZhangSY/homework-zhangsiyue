package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitBuildResponseHandler;
import rich.commandHandler.WaitBuyResponseHandler;

import java.util.Optional;

public interface Place {
    Optional<CommandHandler> nextCommandHandler(Player player);
}
