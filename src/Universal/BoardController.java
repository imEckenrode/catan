package Universal;

import BoardData.*;
import GUI.*;
import Player.Player;
import Player.Item;
import Player.Settlement;
import Player.Road;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardController {

    public static final int FULL_CENTER_X = 262;
    public static final int FULL_CENTER_Y = 266;
    private BoardManager model;
    private BoardView view;
    private Player currentPlayer;
    private ArrayList<Player> otherPlayers;
    Robber robber;
    int vpToWin;

    public BoardController(BoardManager model, BoardView view, int vpToWin){
        this.model = model;
        this.view = view;
        this.vpToWin = vpToWin;

        CatanGUI gui = view.getForm();

        //placing Building Card, and buttons
        BuildingCard buildingCard = new BuildingCard("./CatanPNGs/BuildingCard.png",0,0,200,200);
        buildingCard.drawImage(gui.getBuildingCardPanel());
        gui.getBuildButtons().setOpaque(false);
        gui.getBuildRoadButton().add(gui.ButtonImage("./CatanPNGs/BuildRoad.png",160,50));
        gui.getBuildSettlentButton().add(gui.ButtonImage("./CatanPNGs/BuildSettlement.png",160,50));
        gui.getBuildCityButton().add(gui.ButtonImage("./CatanPNGs/BuildCity.png",160,50));

        gui.getBuildRoadButton().addActionListener(e -> {
            if (currentPlayer.getRoadCount()!=0) {
                currentPlayer.setRoadCount(currentPlayer.getRoadCount() - 1);
                currentPlayer.getHand().removeResource(Catan.Resource.WOOD);
                currentPlayer.getHand().removeResource(Catan.Resource.CLAY);
                updateResourceDisplays();
                model.addToPlacementQueue(new Road(currentPlayer));
            }
        });
        gui.getBuildSettlentButton().addActionListener(e -> {
            if (currentPlayer.getSettlementCount()!=0) {
                currentPlayer.setSettlementCount(currentPlayer.getSettlementCount() - 1);
                currentPlayer.getHand().removeResource(Catan.Resource.WOOD);
                currentPlayer.getHand().removeResource(Catan.Resource.CLAY);
                currentPlayer.getHand().removeResource(Catan.Resource.GRAIN);
                currentPlayer.getHand().removeResource(Catan.Resource.WOOL);
                updateResourceDisplays();
                model.addToPlacementQueue(new Settlement(currentPlayer));
            }
        });
        gui.getBuildCityButton().addActionListener(e -> {
            if (currentPlayer.getCityCount()!=0) {
                currentPlayer.setCityCount(currentPlayer.getCityCount() - 1);
                currentPlayer.getHand().removeResource(Catan.Resource.GRAIN);
                currentPlayer.getHand().removeResource(Catan.Resource.GRAIN);
                currentPlayer.getHand().removeResource(Catan.Resource.ORE);
                currentPlayer.getHand().removeResource(Catan.Resource.ORE);
                currentPlayer.getHand().removeResource(Catan.Resource.ORE);
                updateResourceDisplays();
                model.addToPlacementQueue(new Settlement(model.getCurrentPlayer(), true));
            }
        });

        gui.getBoardPanel().setLayout(null);

        //Loops Though hMap and places all the hexes in the correct spaces visually
        //TODO Verify the i and j values for this loop
        //TODO Make desertHex look right

        int i = 160;
        int j = 100;
        for( Hexagon[] temp : model.hMap) {
            for (Hexagon hex : temp) {
                if (hex == null) {
                    i+=100;
                    continue;
                }
                hex.setCenterX(i);
                hex.setCenterY(j);
                JLabel test = new JLabel("O");
                hex.drawImage(gui.getBoardPanel());
                //hex.getVertex(1).drawImage(gui.getBoardPanel());

                i+=100;
            }
            i-=750;
            j+=85;
        }
        // placing in the background last because null layout
        Board board = new Board("./CatanPNGs/PlainBoard.png",262,262,625,525);
        board.drawImage(gui.getBoardPanel());




        gui.getItemsPanel().setLayout(null);
        gui.getItemsPanel().setOpaque(false);
        robber = new Robber(gui.getItemsPanel());

        //TODO remove road test code
        PlacePNG(gui.getItemsPanel(),"./CatanPNGs/Road0.png",40,40,160,35);
        PlacePNG(gui.getItemsPanel(),"./CatanPNGs/Road.png",40,40,190,80);
        PlacePNG(gui.getItemsPanel(),"./CatanPNGs/Road2.png",40,40,220,35);
        PlacePNG(gui.getItemsPanel(),"./CatanPNGs/City.png",40,40,100,100);

        view.pack();
        view.setSize(840,715);

        //Now for the buttons

        gui.getEndTurnButton().addActionListener(e->{
            nextTurn();
        });
        //Placing port Images
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/3For1Port.png",70,70,95,-10);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/GrainPort.png",70,70,260,-15);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/RockPort.png",70,70,415,85);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/3For1Port.png",70,70,500,230);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/SheepPort.png",70,70,415,390);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/3For1Port.png",70,70,255,465);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/3For1Port.png",70,70,90,465);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/BrickPort.png",70,70,-5,315);
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/GrainPort.png",70,70,-5,135);

        //Port Trading Buttons
        gui.getFour2oneTradeButton().addActionListener(e ->{
            Catan.Resource haveResource = promptResourcePicker(currentPlayer,4);
            bankTrade(haveResource,4);
        });
        gui.getTradeButton(Catan.Resource.DESERT).addActionListener(e -> {
            Catan.Resource haveResource = promptResourcePicker(currentPlayer,3);
            bankTrade(haveResource,3);
        });
        gui.getTradeButton(Catan.Resource.WOOD).addActionListener(e -> {
            bankTrade(Catan.Resource.WOOD,2);
        });
        gui.getTradeButton(Catan.Resource.ORE).addActionListener(e -> {
            bankTrade(Catan.Resource.ORE,2);
        });
        gui.getTradeButton(Catan.Resource.GRAIN).addActionListener(e -> {
            bankTrade(Catan.Resource.GRAIN,2);
        });
        gui.getTradeButton(Catan.Resource.WOOL).addActionListener(e -> {
            bankTrade(Catan.Resource.WOOL,2);
        });
        gui.getTradeButton(Catan.Resource.CLAY).addActionListener(e -> {
            bankTrade(Catan.Resource.CLAY,2);
        });

        gui.getTradeButton(Catan.Resource.WOOD).add(gui.ButtonImage("./CatanPNGs/WoodPort.png",40,40));
        gui.getTradeButton(Catan.Resource.GRAIN).add(gui.ButtonImage("./CatanPNGs/GrainPort.png",40,40));
        gui.getTradeButton(Catan.Resource.WOOL).add(gui.ButtonImage("./CatanPNGs/SheepPort.png",40,40));
        gui.getTradeButton(Catan.Resource.CLAY).add(gui.ButtonImage("./CatanPNGs/BrickPort.png",40,40));
        gui.getTradeButton(Catan.Resource.ORE).add(gui.ButtonImage("./CatanPNGs/RockPort.png",40,40));


        //TODO: Code in buttons dynamically to allow for any number of players
        gui.getHand1Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(0)));
        gui.getHand2Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(1)));
        gui.getHand3Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(2)));

        gui.getCurrentHandPanel().setLayout(null);

        for(Catan.Resource r: Catan.Resource.values()){
            int index = r.toIndex();
            if(r==Catan.Resource.DESERT){continue;}
            view.resourceLabels[index] = new JLabel(String.valueOf(r.toIndex()));
            gui.getCurrentHandPanel().add(view.resourceLabels[index]);
            //gui.getCatanPanel().add(view.resourceLabels[index]);
            view.resourceLabels[index].setBounds(r.toIndex()*52+25,6,500,12);

            PlacePNG(gui.getCurrentHandPanel(),r.getCardFilePath(),90,63,r.toIndex()*52,18);
        }

        gui.getBoardPanel().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                //TODO: REMOVE TESTS
                /*
                gui.getItemsPanel().removeAll();
                gui.getItemsPanel().revalidate();
                gui.getItemsPanel().repaint();      */
                //TODO: Move first line to BuildCard
                //model.addToPlacementQueue(new Road(currentPlayer));

                if(attemptToPlaceItem(gui.getItemsPanel(), model.peekPlacementQueue(), e.getX(), e.getY())){
                    model.removeFirstFromQueue();
                };
            }
        });
    }

    private void updateResourceDisplays() {
        //This refreshes everything after trading
        view.updateResourceValues(currentPlayer.getHand().getAllResourceCounts());
    }

    public void beginGame() {
        //Create new color picker dialog
            //If closed, then we are done inputting people (or just have a Select Number of Players pane first)

        model.players = new ArrayList<>();
        //Abstract these 4 into an actual function call
        ArrayList<Color> standardColors = new ArrayList<>(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        for(int i = 0; i<4; i++){
            //TODO Uncomment
            Color chosenColor =null;//promptColorPicker();
            if(chosenColor == null){
                chosenColor = standardColors.get(i);
            }
            model.players.add(new Player(chosenColor));
        }

        //Roll dice for who goes first (a visual would be nice. If so, remove model.dice.randomInt)

        //cycle until the winner of the dice toss is selected
        for(int i=0; i<model.dice.randomInt(4); i++){
            model.players.add(model.players.remove(0));
        }

        //TODO: Replace local variables with function calls
        setCurrentPlayer(model.getCurrentPlayer());
        otherPlayers = model.getOtherPlayers();

        updateHandColors();
        //Do all road/settlement placements


        model.setGameBegin(true);
        takeTurn(currentPlayer, otherPlayers);
    }


    //Return True if the person won?
    public void takeTurn(Player currentPlayer, ArrayList<Player> otherPlayers) {
        setCurrentPlayer(currentPlayer);
        this.otherPlayers = otherPlayers;
        updateHandColors();
        view.updatePlayerDisplay(currentPlayer);
        rollDiceAndResource();
    }

    private void updateHandColors() {
        view.getForm().getCurrentHandPanel().setBorder(BorderFactory.createLineBorder(currentPlayer.getColor(), 5));
        view.getForm().getHand1Button().setBorder(BorderFactory.createLineBorder( otherPlayers.get(0).getColor(), 5));
        view.getForm().getHand2Button().setBorder(BorderFactory.createLineBorder( otherPlayers.get(1).getColor(), 5));
        view.getForm().getHand3Button().setBorder(BorderFactory.createLineBorder( otherPlayers.get(2).getColor(), 5));
    }

    //public void lavel

    private void rollDiceAndResource() {
        int number = displayDiceRoll();
        //update view and say what number is rolled
        if(number == 7){
            robberEvent();
        }else{
            for(Hexagon hex: model.numberToTile.get(number)){
                hex.distributeResources();
            }
        }
    }

    private int displayDiceRoll(){
        DiceRoll diceDisplay = new DiceRoll(model.dice);
        return diceDisplay.showDialog();
    }

    private void robberEvent() {
        for(Player p: otherPlayers){
            if(p.getHand().totalResourceCount()>7){
                p.getHand().randomLoseHalf(model.dice);
            }
        }
        updateResourceDisplays();
        model.addToPlacementQueue(robber);
    }

    private void nextTurn(){
        if (whoWon(vpToWin) != null) {
            //TODO: DEFINE WIN SCREEN?
            view.getForm().getEndTurnButton().setText("You have won Catan!");
            view.getForm().getEndTurnButton().setEnabled(false);
            System.out.println("You are the settler of Catan!");
        }
        //Add the current player to the back of the list
        model.players.add(model.players.remove(0));
        //Clear the queue of items to place (just in case)
        model.clearPlacementQueue();
        //and take turns
        takeTurn(model.getCurrentPlayer(), model.getOtherPlayers());
        //TODO: Refactor and always use getCurrentPlayer and getOtherPlayers
    }

    private boolean attemptToPlaceItem(JPanel itemsPanel, Item item, int clickX, int clickY) {
        if (item == null) {
            return false;
        }

        //The center of the center hex is adjusted to be 0,0
        // Integer division rounds the values towards 0; all four boxes in the center are 0,0 to create symmetry
        int x = (clickX - FULL_CENTER_X) / 25;
        int y = (clickY - FULL_CENTER_Y) / 25;

        int yHex = (int) Math.signum(y) * (Math.abs(y) < 2 ? 0 : Math.abs(y) < 5 ? 1 : 2);
        int xHex = switch (Math.abs(yHex)) {
            case 0 ->     //For five hexagons in the row
                    (int) Math.signum(x) * (Math.abs(x) < 2 ? 0 : Math.abs(x) < 6 ? 1 : 2);
            case 1 ->     //For four hexagons in the row
                    (int) Math.signum(x) * (Math.abs(x) < 4 ? 0 : 1) + (Math.signum(x) < 0 ? -1 : 0);
            case 2 ->     //For three hexagons in the row
                    (int) Math.signum(x) * (Math.abs(x) < 2 ? 0 : 1);
            default -> 0;
        };

        yHex += 2; //Now yHex is in our standard coordinate system
        xHex += Math.floorDiv((yHex - 1), 2) + 2; //Now xHex is in our standard coordinate system
        System.out.println(yHex + "," + xHex);

        Hexagon foundHex = model.hMap[yHex][xHex];
        //At this point, we have the hexagon coordinates. If we only wanted the hexagon, execute the hexagon method
        //Otherwise, find further

        /*Type sniffing and more is a code smell and should be fixed in the future.
        However, this assignment is due very soon, so MVP it is
          Technically, Road is EdgeItem, since it is always used for Edge, and same for Settlement and Vertex */

        //TokenNum should not be included here
        if (item instanceof Robber) {
            if(foundHex.getResourceType() == Catan.Resource.DESERT){
                return false;
            }
            robber.moveTo(foundHex);
            //TODO: Implement robber plunder with a selected settlement on the hex
            //robber.selectPlunder(foundHex, currentPlayer);
            return true;
        }

        double degrees = foundHex.getAngle(clickX, clickY);
        if(item instanceof Settlement s){
            Vertex v = foundHex.getVertexFromDegrees(degrees);
            //If this is a city, then only place it on a currentPlayer-owned settlement
            if(s.isACity()){
                if(!v.hasSettlement() || v.getSettlement().getOwner() != item.getOwner()){
                    return false;
                }else {
                    view.getForm().getItemsPanel().remove(v.getLabel());
                    v.placeSettlement(s, itemsPanel);
                    item.getOwner().raiseSettlementCount();
                    item.getOwner().lowerCityCount();
                    return true;
                }
            }else{  //Otherwise, be sure to check if the settlement placement is legal
                if(isLegalPlacement(foundHex, v, item.getOwner())) {
                    v.placeSettlement((Settlement) item, itemsPanel);
                    //TODO: Collect Port if available
                    item.getOwner().lowerSettlementCount();
                    return true;
                }
            }

        }else if(item instanceof Road){
            Edge e = foundHex.getEdgeFromDegrees(degrees);
            if(isLegalPlacement(foundHex, e, item.getOwner())){
                e.placeRoad((Road) item, itemsPanel);
                item.getOwner().lowerRoadCount();
                return true;
            }
        }else{
            System.out.println("How did we get here?");
            return true;
            //System.exit(1);
        }

        return false;
        /*
        for(int i = 0; i<6;i++){
            Edge e = model.hMap[yHex][xHex].getEdge(i);
            //System.out.print(v+", ");
            if(e.getRoad() == null){
                e.setRoad((Road) item, itemsPanel, i);
            }else{
                e.setRoad((Road) item, itemsPanel, i);
                System.out.print(e.getRoad());
            }
        }
         */
    }

    //TODO: Change the name of currentPlayer to simply player?
    private boolean isLegalPlacement(Hexagon foundHex, Vertex v, Player currentPlayer) {
        int dir = foundHex.getVertexDir(v);
        //First, check if there are no adjacent settlements
        if(foundHex.getVertex((dir+1)%6).hasSettlement()
                || foundHex.getVertex((dir+5)%6).hasSettlement()
                || v.hasSettlement()){
            return false;
        }
        if(foundHex.getOutsideVertex(dir) != null){ //Need a special case in case outsideVertex doesn't exist
            if(foundHex.getOutsideVertex(dir).hasSettlement()){
                return false;
            }
        }
        //Next, check for adjacent current-player-owned roads, but only if initial placements are done
        if(model.didGameBegin()){
            for(Edge e: new ArrayList<>(Arrays.asList(foundHex.getEdge(dir), foundHex.getEdge((dir+5)%6),foundHex.getOutsideEdge(dir)))){
                if (e != null) {
                if(e.hasRoad()){
                if(e.getRoad().getOwner() == currentPlayer){
                    return true;
                }}}
            }
            return false;
        }else{
            return true;
        }
    }

    private boolean isLegalPlacement(Hexagon foundHex, Edge v, Player currentPlayer) {
        return true;
    }


    //PlacePNG take a .png file, turns it into a Buffered Image, then turns the Buffered image into a ImageIcon,
    // then resizes the ImageIcon, then turns the resized ImageIcon into a JLabel, Then adds the JLabel to the desired Panel,
    //THEN it sets the bounds on said Label. All in like 8 lines. Thank you, Good night.
    public void PlacePNG(JPanel panel, String file, int height, int width, int gridx, int gridy){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel label = new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        panel.add(label);
        label.setBounds(gridx,gridy,width,height);
    }
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        view.updatePlayerDisplay(currentPlayer);
    }

    private Player whoWon(int vpToWin) {
        for(Player p: model.players){
            if(p.getVictoryPoints()>vpToWin-1) {
                return p;
            }
        }
        return null;
    }

    private Catan.Resource promptResourcePicker(Player player, int numberSelecting){
        ResourcePicker picker = new ResourcePicker(player, numberSelecting);
        Catan.Resource picked = picker.showDialog();
        updateResourceDisplays();
        return picked;
    }
    private Catan.Resource promptResourcePicker(){
        ResourcePicker picker = new ResourcePicker();
        Catan.Resource picked = picker.showDialog();
        updateResourceDisplays();
        return picked;
    }

    private Color promptColorPicker(){
        return new PlayerColorPicker().showDialog();
    }
    private void tradeWithPlayer(Player player) {
        Catan.Resource have = promptResourcePicker(currentPlayer, 1);
        if(have==null){
            return;
        }
        Catan.Resource want = promptResourcePicker();
        if(want==null){
            return;
        }
        //Could add Accept or Decline Trade before completing trade (optional to code in)
        currentPlayer.getHand().removeResource(have,1);
        player.getHand().addResource(have);
        player.getHand().removeResource(want,1);
        currentPlayer.getHand().addResource(want);
        updateResourceDisplays();
    }
    public void bankTrade(Catan.Resource have, int num){
        currentPlayer.getHand().removeResource(have,num);
        currentPlayer.getHand().addResource(promptResourcePicker());
        updateResourceDisplays();

    }
}
