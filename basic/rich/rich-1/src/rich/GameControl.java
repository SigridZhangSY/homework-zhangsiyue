package rich;

import java.util.List;

public class GameControl {
    private Map map;
    private Dice dice;
    private Player currentPlayer;
    private List<Player> players;
    private double initBalance;
    private GameState state;

    public GameControl(){
        state = GameState.WAIT_INIT_BALANCE;
        initBalance = 10000;
    }

    public boolean setInitBalance(double initBalance){
        if(1000 <= initBalance && 50000 >= initBalance) {
            this.initBalance = initBalance;
            state = GameState.WAIT_INIT_PLAYER;
            return true;
        }
        return false;
    }

    public double getInitBalance() {
        return initBalance;
    }

    public GameState getState() {
        return state;
    }

    public enum GameState{
        WAIT_INIT_BALANCE,
        WAIT_INIT_PLAYER,
        IN_PROCESS,
        END_GAME
    }
}
