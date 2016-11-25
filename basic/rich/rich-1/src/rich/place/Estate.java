package rich.place;

import rich.Player;
import rich.commandHandler.CommandHandler;
import rich.commandHandler.WaitBuildResponseHandler;
import rich.commandHandler.WaitBuyResponseHandler;

import java.util.Optional;

public class Estate implements Place {

    private Player owner;

    private double price;

    private LEVEL level;

    public Estate(Player owner) {
        this.owner = owner;
    }

    public Estate(double price) {
        this.price = price;
        level = LEVEL.ZERO;
    }

    public static Estate createEstateWithLevel(double price, LEVEL level){
        Estate estate = new Estate(price);
        estate.level = level;
        return estate;
    }

    public Optional<CommandHandler> nextCommandHandler(Player player){
        if (owner == null)
            return Optional.of(new WaitBuyResponseHandler());
        if(owner.equals(player))
            return Optional.of(new WaitBuildResponseHandler());
        return Optional.empty();
    }

    public void buy(Player player){
        owner = player;
    }

    public boolean build(){
        if (level.equals(LEVEL.TOP))
            return false;
        level = level.next();
        return true;
    }

    public double getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public LEVEL getLevel() {
        return level;
    }

    public enum LEVEL{
        ZERO {
            @Override
            public LEVEL next() {
                return ONE;
            }
        },
        ONE {
            @Override
            public LEVEL next() {
                return TWO;
            }
        },
        TWO {
            @Override
            public LEVEL next() {
                return TOP;
            }
        },
        TOP {
            @Override
            public LEVEL next() {
                return TOP;
            }
        };

        public abstract LEVEL next();
    }

}
