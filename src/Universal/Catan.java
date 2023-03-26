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
        WOOD(0,"Wood","./CatanPNGs/WoodHex.png","./CatanPNGs/wood.png"),
        ORE(1,"Ore", "./CatanPNGs/Rock.png","./CatanPNGs/ore.png"),
        GRAIN(2,"Grain","./CatanPNGs/GrainHex.png","./CatanPNGs/grain.png"),
        WOOL(3,"Sheep","./CatanPNGs/SheepHex.png","./CatanPNGs/sheep.png"),
        CLAY(4,"Brick","./CatanPNGs/BrickHex.png","./CatanPNGs/brick.png"),
        DESERT (-1,"Desert","./CatanPNGs/desert.png",null);
        //TODO rename desert.png to desertHex.png
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
        controller.beginGame();
    }

    public static void main(String[] args) {
        int vpToWin = 10;
        Catan game = new Catan(vpToWin);
        //game.playGame(vpToWin);
    }

    /*

     */
}