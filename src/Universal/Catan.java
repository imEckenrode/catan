package Universal;

import BoardData.BoardManager;
import Player.Player;

import java.util.ArrayList;

public class Catan {

    ArrayList<Player> players;
    Dice dice;
    BoardManager boardManager;
    public enum Resource{DESERT,WOOD,ORE,GRAIN,WOOL,CLAY} //Could call it NONE instead

    public Catan() {
        players = new ArrayList<>();    //Choose your colors then add
        dice = new Dice();
        boardManager = new BoardManager();
    }

    public static void main(String[] args) {
        Catan game = new Catan();
    }

}