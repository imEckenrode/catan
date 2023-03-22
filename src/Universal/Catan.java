package Universal;

import BoardData.BoardManager;
import GUI.BoardView;
import Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Catan {

    ArrayList<Player> players;
    public enum Resource{DESERT,WOOD,ORE,GRAIN,WOOL,CLAY} //Could call it NONE instead
    BoardController controller;

    public Catan() {
        BoardView view = new BoardView();
        BoardManager model = new BoardManager();
        BoardController controller = new BoardController(model,view);

        view.setVisible(true);

        players = new ArrayList<>();    //Choose your colors then add
        //Abstract these 4 into an actual function call
        players.add(new Player(Color.RED));
        players.add(new Player(Color.YELLOW));
        players.add(new Player(Color.BLUE));
        players.add(new Player(Color.GREEN));

        Dice dice = new Dice();      //This will get added to Controller most likely

        //Snake to start
        //Now play the game!
    }

    public static void main(String[] args) {
        Catan game = new Catan();
        int vpToWin = 10;
        game.playGame(vpToWin);
    }

    private Player playGame(int vpToWin) {
        //TODO: Create a loop and use takeTurn to return true if that person won?
        while(whoWon(vpToWin) == null){
            //Also includes rolling and distributing
            controller.takeTurn(getCurrentPlayer(),(ArrayList<Player>) players.subList(1,players.size()));
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
}