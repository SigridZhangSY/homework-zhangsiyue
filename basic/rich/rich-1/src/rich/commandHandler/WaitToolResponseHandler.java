package rich.commandHandler;

import rich.Player;
import rich.Tool;

import java.util.Optional;

public class WaitToolResponseHandler implements CommandHandler {
    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        if(input.equalsIgnoreCase("f"))
            return Optional.empty();
        int choice = Integer.valueOf(input) - 1;
        if(player.getPoints() >= Tool.ToolType.values()[choice].getPrice())
            player.buyTool(choice);
        if (player.getTools().size() == 10 || player.getPoints() < 30)
            return Optional.empty();
        return Optional.of(new WaitToolResponseHandler());
    }
}
