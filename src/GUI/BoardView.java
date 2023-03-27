package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class BoardView extends JFrame {
    public CatanGUI form;
    public JLabel[] resourceLabels;

    public BoardView(){
        this.form = new CatanGUI();
        JPanel content = form.getCatanPanel();
        resourceLabels = new JLabel[5];

        this.setContentPane(content);
        this.pack();
        this.setTitle("YOOOO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CatanGUI getForm(){return form;}

    public void newResourceValues(ArrayList<Integer> allResourceCounts) {
        int i = 0;
        for(JLabel l: resourceLabels){
            l.setText(String.valueOf(allResourceCounts.get(i)));
            i++;
        }
    }
}
