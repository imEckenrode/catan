package Universal;

import BoardData.BoardManager;
import GUI.BoardView;
import Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Catan {

    ArrayList<Player> players;
    BoardController controller;
    public int vpToWin;
    public enum Resource{
        DESERT (-1),
        WOOD(0),
        ORE(1),
        GRAIN(2),
        WOOL(3),
        CLAY(4);

        private final int index;
        Resource(int index) {this.index = index;}
        public int toIndex(){return index;}
    }

    public Catan() {
        BoardView view = new BoardView();
        BoardManager model = new BoardManager();
        controller = new BoardController(model,view);

        view.setVisible(true);

        players = new ArrayList<>();    //Choose your colors then add
        //Abstract these 4 into an actual function call
        players.add(new Player(Color.RED));
        players.add(new Player(Color.YELLOW));
        players.add(new Player(Color.BLUE));
        players.add(new Player(Color.GREEN));

        //Now play the game!
    }

    public static void main(String[] args) {
        int vpToWin = 10;
        Catan game = new Catan();
        //game.playGame(vpToWin);
    }

    /*
    private Player playGame(int vpToWin) {
        //TODO: This should be done at the BoardController level
        while(whoWon(vpToWin) == null){
            //Also includes rolling and distributing
            controller.takeTurn(getCurrentPlayer(), new ArrayList<>(players.subList(1,players.size())));
            //This returns when done with turn
            nextTurn();
        }
        return whoWon(vpToWin);
    }

    private Player whoWon(int vpToWin) {
        for(Player p: players){
            if(p.getVictoryPoints()>vpToWin-1) {
                return p;
            }
        }
        return null;
    }

    public Player getCurrentPlayer(){
        return players.get(0);
    }

    private void nextTurn(){
        //Add the current player to the back of the list
        players.add(players.remove(0));
    }
     */
}