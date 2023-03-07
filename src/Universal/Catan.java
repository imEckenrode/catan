package Universal;

import BoardData.BoardManager;
import Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Catan {

    ArrayList<Player> players;
    Dice dice;
    BoardManager boardManager;
    public enum Resource{DESERT,WOOD,ORE,GRAIN,WOOL,CLAY} //Could call it NONE instead

    public Catan() {
        players = new ArrayList<>();    //Choose your colors then add
        //Abstract these 4 into an actual function call
        players.add(new Player(Color.RED));
        players.add(new Player(Color.YELLOW));
        players.add(new Player(Color.BLUE));
        players.add(new Player(Color.GREEN));

        dice = new Dice();
        boardManager = new BoardManager();

        //Snake to start

        //Now play the game!
    }

    public static void main(String[] args) {
        Catan game = new Catan();
    }

}