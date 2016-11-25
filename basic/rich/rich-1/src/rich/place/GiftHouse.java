package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitGiftResponseHandler;

import java.util.Optional;

public class GiftHouse implements Place {
    @Override
    public Optional<CommandHandler> nextCommandHandler(Player player) {
        return Optional.of(new WaitGiftResponseHandler());
    }
}
