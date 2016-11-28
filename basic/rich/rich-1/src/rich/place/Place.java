package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitBuildResponseHandler;
import rich.commandHandler.WaitBuyResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Place {

    protected boolean isBlocked;
    protected boolean isBombed;
    protected List<Player> players;

    public Place() {
        this.isBlocked = false;
        this.isBombed = false;
        players = new ArrayList<>();
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

    public void clearTool(){
        isBlocked = false;
        isBombed = false;
    }

    public void arrive(Player player){
        players.add(player);
    }

    public void leave(Player player){
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
