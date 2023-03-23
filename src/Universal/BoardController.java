package Universal;

import BoardData.BoardManager;
import BoardData.Hexagon;
import GUI.BoardView;
import GUI.CatanGUI;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BoardController {

    private BoardManager model;
    private BoardView view;
    private Player currentPlayer;
    private ArrayList<Player> otherPlayers;

    public BoardController(BoardManager model, BoardView view){
        this.model = model;
        this.view = view;

        CatanGUI gui = view.getForm();

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./CatanPNGs/PlainBoard.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel label = new JLabel(new ImageIcon(image));

        //gui.getBoardPanel().add(label);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=21;
        gbc.gridheight = 25;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.weightx = 1;
        gui.getBoardPanel().add(label, gbc);

        /*
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


    }

    //Return True if the person won?
    public void takeTurn(Player currentPlayer, ArrayList<Player> otherPlayers) {
        setCurrentPlayer(currentPlayer);
        this.otherPlayers = otherPlayers;
        rollDiceAndResource();
        //DO STUFF AND RETURN ONCE DONE
        }

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

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


}
