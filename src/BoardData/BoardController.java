package BoardData;

import GUI.BoardView;
import GUI.CatanGUI;

import javax.swing.*;
import java.awt.*;

public class BoardController {

    private BoardManager model;
    private BoardView view;

    public BoardController(BoardManager model, BoardView view){
        this.model = model;
        this.view = view;

        CatanGUI gui = view.getForm();
        // DO STUFF HERE


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth=14;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //.weightx = 1;
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
    }
}
