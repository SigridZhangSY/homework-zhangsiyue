package rich.commandHandler;

import rich.Player;

import java.util.Optional;

public class WaitGiftResponseHandler implements CommandHandler {
    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        int choice = Integer.valueOf(input);
        player.selectGift(choice);
        return Optional.empty();
    }
}
