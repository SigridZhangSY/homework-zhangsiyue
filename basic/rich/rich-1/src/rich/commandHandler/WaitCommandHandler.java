package rich.commandHandler;

import rich.Dice;
import rich.Map;
import rich.Player;
import rich.Tool;
import rich.place.Estate;
import rich.place.Place;

import java.util.Optional;

public class WaitCommandHandler implements CommandHandler{
    private Map map;
    private Dice dice;

    public WaitCommandHandler(Map map, Dice dice) {
        this.map = map;
        this.dice = dice;
    }


    @Override
    public Optional<CommandHandler> execute(String input, Player player) {
        String[] inputs = input.split(" ");
        if(inputs[0].equalsIgnoreCase("roll")) {
            player.moveTo(map, dice);
            return player.getCurrentPlace().nextCommandHandler(player);
        }

        if(inputs[0].equalsIgnoreCase("sell")) {
            int position = Integer.valueOf(inputs[1]);
            Place place = map.getPlace(position);
            if(place instanceof Estate){
                Estate estate = (Estate)place;
                if(estate.getOwner() == player)
                    player.sellEstate(estate);
            }
            return Optional.of(new WaitCommandHandler(map, dice));
        }

        if(inputs[0].equalsIgnoreCase("sellTool")){
            int choice = Integer.valueOf(inputs[1]) - 1;
            player.sellTool(choice);
            return Optional.of(new WaitCommandHandler(map, dice));
        }

        if(inputs[0].equalsIgnoreCase("block")){
            Place place = map.getPlace(player.getCurrentPlace(), Integer.valueOf(inputs[1]));
            player.userTool(Tool.ToolType.BLOCK, place, map);
            return Optional.of(new WaitCommandHandler(map, dice));
        }

        if(inputs[0].equalsIgnoreCase("bomb")){
            Place place = map.getPlace(player.getCurrentPlace(), Integer.valueOf(inputs[1]));
            player.userTool(Tool.ToolType.BOMB, place, map);
            return Optional.of(new WaitCommandHandler(map, dice));
        }

        if(inputs[0].equalsIgnoreCase("robot")){
            player.userTool(Tool.ToolType.ROBOT, null, map);
            return Optional.of(new WaitCommandHandler(map, dice));
        }
        return Optional.empty();
    }
}
