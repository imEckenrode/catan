package GUI;

import javax.swing.*;

public class BoardView extends JFrame {
    public CatanGUI form;

    public BoardView(){
        this.form = new CatanGUI();
        JPanel content = form.getCatanPanel();

        this.setContentPane(content);
        this.pack();
        this.setTitle("YOOOO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CatanGUI getForm(){return form;}
}
