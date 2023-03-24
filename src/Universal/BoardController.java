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






        //Taking the files from CatanPNGs and turning them into Buffered Images
        BufferedImage BuildingCardImage = null;
        BufferedImage BoardImage = null;
        BufferedImage BrickHexImage = null;

        try {
            BoardImage = ImageIO.read(new File("./CatanPNGs/PlainBoard.png"));
            BuildingCardImage = ImageIO.read((new File("./CatanPNGs/BuildingCard.png")));
            BrickHexImage = ImageIO.read((new File("./CatanPNGs/BrickHex.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // resizing

        JLabel BuildingCardLabel = new JLabel(new ImageIcon(BuildingCardImage));


        gui.getBuildingCardPanel().add(BuildingCardLabel);
        //gui.getBoardPanel().add(BoardImageLabel);

        JLabel BackgroundLabel = makeImage("./CatanPNGs/PlainBoard.png",625,525);
        gui.getBoardPanel().add(BackgroundLabel);
        BackgroundLabel.setBounds(0,0,525,625);
        for( Hexagon[] temp : model.hMap){
            for (Hexagon i : temp){

            }

        }

/*
        gui.getBoardPanel().setLayout(null);
        JLabel BrickHexLabel = new JLabel(BrickHexIcon);
        gui.getBoardPanel().add(BrickHexLabel);
        BrickHexLabel.setBounds(200,200,100,100);

        BrickHexLabel = new JLabel(BrickHexIcon);
        gui.getBoardPanel().add(BrickHexLabel);
        BrickHexLabel.setBounds(300,200,100,100);
        BrickHexLabel = new JLabel(BrickHexIcon);
        gui.getBoardPanel().add(BrickHexLabel);
        BrickHexLabel.setBounds(400,200,100,100);

       // gui.getBoardPanel().add(BoardImageLabel);
       // BoardImageLabel.setBounds(0,0,525,625);



            /*
        GridBagConstraints gbc = new GridBagConstraints();
       // gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth=21;
        gbc.gridheight = 25;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.weightx = 1;
        JPanel aaa = new JPanel();
        aaa.add(BoardImageLabel);
        gui.getBoardPanel().add(aaa, gbc);
        //gui.getBoardPanel().add(BoardImageLabel, gbc);


        //gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth=4;
        gbc.gridheight = 4;
        gbc.gridx = 25;
        gbc.gridy = 25;
        //gbc.weightx = 1;
        JPanel zeroZero = new JPanel();
        zeroZero.add(BrickHexLabel);
        gui.getBoardPanel().add(zeroZero, gbc);


        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth=1;
        gbc.gridheight = 14; // Actually 15
        gbc.gridx = 0;
        gbc.gridy = 1;
       //gbc.weightx = 1;
        JLabel test2 = new JLabel("A");
        gui.getBoardPanel().add(test2, gbc);

            */
        view.pack();

        view.setSize(840,715);

        //All the hexagons
        JPanel firstHex = new JPanel();

        gui.getEndTurnButton().addActionListener(e->{
            nextTurn();
        });

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
    public JLabel makeImage(String file,int height, int width ){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT))));
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
        ResourcePicker picker = new ResourcePicker();
        return picker.showDialog();
    }

}
