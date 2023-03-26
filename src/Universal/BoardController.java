package Universal;

import BoardData.BoardManager;
import BoardData.Hexagon;
import GUI.BoardView;
import GUI.CatanGUI;
import GUI.ResourcePicker;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        //Loops Though hMap and places all the hexes in the correct spaces
        //TODO Verify the i and j values for this loop
        double i = 1.15;
        double j = 1;
        for( Hexagon[] temp : model.hMap) {
            for (Hexagon hex : temp) {
                if (hex == null) {
                    i++;
                    continue;
                }
                PlacePNG(gui.getBoardPanel(), hex.getResourceType().getHexFilePath(), 100, 100, (int)(i * 100), (int)(j * 100));
                i++;
            }
            i-=7.5;
            j+=.85;
        }

        // placing in the background last because null layout
        PlacePNG(gui.getBoardPanel(),"./CatanPNGs/PlainBoard.png",625,525,0,0);

        view.pack();
        view.setSize(840,715);

        //Now for the clicking functionality


        gui.getEndTurnButton().addActionListener(e->{
            nextTurn();
        });

        //Could code button population dynamically, but it is nice to see the buttons in Form Builder
        gui.getHand1Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(0)));
        gui.getHand2Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(1)));
        gui.getHand3Button().addActionListener(e -> tradeWithPlayer(otherPlayers.get(2)));

        //gui.getHand1Panel().getHand
    }


    //Return True if the person won?
    public void takeTurn(Player currentPlayer, ArrayList<Player> otherPlayers) {
        setCurrentPlayer(currentPlayer);
        this.otherPlayers = otherPlayers;
        rollDiceAndResource();
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
            //TODO: Update hand with a refresh of some sort
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
        takeTurn(model.getCurrentPlayer(), new ArrayList<>(model.players.subList(1,model.players.size())));

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
        return picker.showDialog();
    }
    private Catan.Resource promptResourcePicker(){
        ResourcePicker picker = new ResourcePicker();
        return picker.showDialog();
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
        currentPlayer.getHand().removeResource(have);
        player.getHand().addResource(have);
        player.getHand().removeResource(want);
        currentPlayer.getHand().addResource(want);
    }
}
