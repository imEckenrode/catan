package Universal;

import BoardData.BoardManager;
import GUI.BoardView;
import GUI.CatanGUI;

import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardController {

    private BoardManager model;
    private BoardView view;

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

        gui.getBoardPanel().add(label);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth=14;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.weightx = 1;
        JLabel test1 = new JLabel("Hello, World!");
        gui.getBoardPanel().add(test1, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth=1;
        gbc.gridheight = 14; // Actually 15
        gbc.gridx = 0;
        gbc.gridy = 1;
       //gbc.weightx = 1;
        JLabel test2 = new JLabel("A");
        gui.getBoardPanel().add(test2, gbc);

        view.pack();


        view.setSize(1008,720);

        //All the hexagons
        JPanel firstHex = new JPanel();


    }
}
