package Universal;

import BoardData.BoardManager;
import GUI.BoardView;
import Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Catan {

    BoardController controller;
    public int vpToWin;
    public enum Resource{
        WOOD(0,"Wood","./CatanPNG/WoodHex.png","./CatanPNGs/wood.png"),
        ORE(1,"Ore", "./CatanPNG/Rock.png","./CatanPNGs/ore.png"),
        GRAIN(2,"Grain","./CatanPNG/GrainHex.png","./CatanPNGs/grain.png"),
        WOOL(3,"Sheep","./CatanPNG/SheepHex.png","./CatanPNGs/sheep.png"),
        CLAY(4,"Brick","./CatanPNG/BrickHex.png","./CatanPNGs/brick.png"),
        DESERT (-1,"Desert","./CatanPNG/FullBoard.jpg",null);

        private final int index;
        private final String name;
        private final String hexagonFile;
        private final String cardFile;
        Resource(int index, String name, String hexagonFile, String cardFile) {this.index = index; this.name = name; this.hexagonFile = hexagonFile; this.cardFile = cardFile;}
        public int toIndex(){return index;}
        public String getHexFilePath(){return hexagonFile;}
        public String getName(){return name;}
        public String getCardFilePath(){return cardFile;}
    }

    public Catan(int vpToWin) {
        BoardView view = new BoardView();
        BoardManager model = new BoardManager();
        controller = new BoardController(model,view,vpToWin);

        view.setVisible(true);

        //TODO: Move this into BoardController

        model.players = new ArrayList<>();    //Choose your colors then add
        //Abstract these 4 into an actual function call
        model.players.add(new Player(Color.RED));
        model.players.add(new Player(Color.YELLOW));
        model.players.add(new Player(Color.BLUE));
        model.players.add(new Player(Color.GREEN));

        //Now play the game!
    }

    public static void main(String[] args) {
        int vpToWin = 10;
        Catan game = new Catan(vpToWin);
        //game.playGame(vpToWin);
    }

    /*

     */
}