package Universal;

import BoardData.BoardManager;
import BoardData.Hexagon;
import GUI.BoardView;
import GUI.CatanGUI;
import GUI.PlayerColorPicker;
import GUI.ResourcePicker;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardController {

    private BoardManager model;
    private BoardView view;
    private Player currentPlayer;
    private ArrayList<Player> otherPlayers;
    int vpToWin;

    public BoardController(BoardManager model, BoardView view, int vpToWin){
        this.model = model;
        this.view = view;
        this.vpToWin = vpToWin;

        CatanGUI gui = view.getForm();

        //placing Building Card in correct Spot
        //TODO resize this building card
        PlacePNG(gui.getBuildingCardPanel(),"./CatanPNGs/BuildingCard.png",100,100,0,0);


        gui.getBoardPanel().setLayout(null);

        //Loops Though hMap and places all the hexes in the correct spaces visually
        //TODO Verify the i and j values for this loop
        //TODO Make desertHex look right
        PlacePNG(gui.getBoardPanel(), "./CatanPNGs/Settlement.png",30,30,95,105);
        PlacePNG(gui.getBoardPanel(), "./CatanPNGs/Settlement.png",30,30,300,225);
        double i = 1.15;
        double j = 0.5;
        for( Hexagon[] temp : model.hMap) {
            for (Hexagon hex : temp) {
                if (hex == null) {
                    i++;
                    continue;
                }
                hex.setCenterX((int)(i*100+32));
                hex.setCenterY((int)(j*100));
                JLabel test = new JLabel("O");
                PlacePNG(gui.getBoardPanel(), hex.getResourceType().getHexFilePath(), 100, 100, (int)(i * 100), (int)(j * 100));
                i++;
            }
            i-=7.5;
            j+=.85;
        }
        // placing in the background last because null layout

        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/PlainBoard.png",625,525,5,-50);

        view.pack();
        view.setSize(840,715);

        //Now for the buttons

        gui.getEndTurnButton().addActionListener(e->{
            nextTurn();
        });

        gui.getFour2oneTradeButton().addActionListener(e ->{
            Catan.Resource haveResource = promptResourcePicker(currentPlayer,4);
            currentPlayer.getHand().removeResource(haveResource,4);
            Catan.Resource wantResource = promptResourcePicker();
            currentPlayer.getHand().addResource(wantResource);
            updateResourceDisplays();
        });
//

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

        //gui.getHand1Panel().getHand
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
            Color chosenColor = promptColorPicker();
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

        //Do all road/settlement placements

        updateHandColors();
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
        int number = model.dice.rollDice();
        System.out.print(number);
        //update view and say what number is rolled
        if(number == 7){
            //robberEvent();   //TODO: IMPLEMENT
        }else{
            for(Hexagon hex: model.numberToTile.get(number)){
                hex.distributeResources();
            }
        }
    }

    private void nextTurn(){
        if (whoWon(vpToWin) != null) {
            //TODO: DEFINE WIN SCREEN
            view.getForm().getEndTurnButton().setText("You have won Catan!");
            view.getForm().getEndTurnButton().setEnabled(false);
            System.out.println("You are the settler of Catan!");
        }
        //Add the current player to the back of the list
        model.players.add(model.players.remove(0));
        //and take turns
        takeTurn(model.getCurrentPlayer(), model.getOtherPlayers());
        //TODO: Refactor and always use getCurrentPlayer and getOtherPlayers

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
}
