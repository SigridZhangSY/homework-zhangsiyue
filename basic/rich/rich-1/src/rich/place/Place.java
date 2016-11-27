package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitBuildResponseHandler;
import rich.commandHandler.WaitBuyResponseHandler;

import java.util.Optional;

public class Place {

    protected boolean isBlocked;
    protected boolean isBombed;

    public Place() {
        this.isBlocked = false;
        this.isBombed = false;
    }

    public Optional<CommandHandler> nextCommandHandler(Player player){
        return null;
    }

    public boolean setBlock(){
        if(!isBlocked && !isBombed){
            isBlocked = true;
            return true;
        }
        return false;
    }

    public boolean setBomb(){
        if(!isBombed && !isBlocked){
            isBombed = true;
            return true;
        }
        return false;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isBombed() {
        return isBombed;
    }
}
