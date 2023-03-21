package Universal;

import BoardData.BoardController;
import BoardData.BoardManager;
import GUI.BoardView;
import GUI.CatanGUI;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Catan {

    ArrayList<Player> players;
    Dice dice;
    BoardManager boardManager;
    public enum Resource{DESERT,WOOD,ORE,GRAIN,WOOL,CLAY} //Could call it NONE instead

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

        dice = new Dice();
        boardManager = new BoardManager();



        //Snake to start

        //Now play the game!
    }

    public static void main(String[] args) {
        Catan game = new Catan();
    }

}