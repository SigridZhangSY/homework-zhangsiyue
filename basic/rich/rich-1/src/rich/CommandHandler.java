package rich;

import java.util.Optional;

public interface CommandHandler {
    Optional<CommandHandler> execute(String input, Player player);
}
