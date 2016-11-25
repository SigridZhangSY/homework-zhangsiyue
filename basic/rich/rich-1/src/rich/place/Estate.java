package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitBuildResponseHandler;
import rich.commandHandler.WaitBuyResponseHandler;

import java.util.Optional;

public class Estate implements Place {

    private Player owner;

    public Estate(Player owner) {
        this.owner = owner;
    }

    public Optional<CommandHandler> nextCommandHandler(Player player){
        if (owner == null)
            return Optional.of(new WaitBuyResponseHandler());
        if(owner.equals(player))
            return Optional.of(new WaitBuildResponseHandler());
        return Optional.empty();
    }
}
