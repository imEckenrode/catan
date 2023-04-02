package Universal;

import BoardData.BoardManager;
import GUI.BoardView;
import Player.Road;
import Player.Settlement;

public class Catan {

    BoardController controller;
    public int vpToWin;
    public enum Resource{
        WOOD(0,"Wood","./CatanPNGs/WoodHex.png","./CatanPNGs/wood.png", null),
        ORE(1,"Ore", "./CatanPNGs/Rock.png","./CatanPNGs/ore.png", null),
        GRAIN(2,"Grain","./CatanPNGs/GrainHex.png","./CatanPNGs/grain.png", null),
        WOOL(3,"Sheep","./CatanPNGs/SheepHex.png","./CatanPNGs/sheep.png", null),
        CLAY(4,"Brick","./CatanPNGs/BrickHex.png","./CatanPNGs/brick.png", null),
        DESERT (-1,"Desert","./CatanPNGs/desert.png",null, null);
        //TODO rename desert.png to desertHex.png
        private final int index;
        private final String name;
        private final String hexagonFile;
        private final String cardFile;
        private final String portFile;
        Resource(int index, String name, String hexagonFile, String cardFile, String portFile) {this.index = index; this.name = name; this.hexagonFile = hexagonFile; this.cardFile = cardFile;
            this.portFile = portFile;
        }
        public int toIndex(){return index;}
        public String getHexFilePath(){return hexagonFile;}
        public String getName(){return name;}
        public String getCardFilePath(){return cardFile;}

        public String getPortFilePath(){return portFile;}
    }

    public Catan(int vpToWin) {
        BoardView view = new BoardView();
        BoardManager model = new BoardManager();
        controller = new BoardController(model,view,vpToWin);

        view.setVisible(true);
        controller.beginGame();

//        model.addToPlacementQueue(new Settlement(model.getCurrentPlayer()));
//        model.addToPlacementQueue(new Settlement(model.getCurrentPlayer(), true));
//        model.addToPlacementQueue(new Road(model.getCurrentPlayer()));
    }

    public static void main(String[] args) {
        int vpToWin = 10;
        Catan game = new Catan(vpToWin);
        //game.playGame(vpToWin);
    }
}