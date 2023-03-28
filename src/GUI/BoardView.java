package GUI;

import Universal.Catan;

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

    public void updatePlayerDisplay(Player.Player currentPlayer){
        updateResourceValues(currentPlayer.getHand().getAllResourceCounts());
        updatePlayerPorts(currentPlayer);
    }

    public void updateResourceValues(ArrayList<Integer> allResourceCounts) {
        int i = 0;
        for(JLabel l: resourceLabels){
            l.setText(String.valueOf(allResourceCounts.get(i)));
            i++;
        }
    }

    private void updatePlayerPorts(Player.Player currentPlayer){
        for(Catan.Resource r: Catan.Resource.values()){
            form.getTradeButton(r).setVisible(currentPlayer.hasResourcePort(r));
        }
        form.getPortsPanel().revalidate();
    }
}
