package Universal;

import BoardData.Hexagon;
import Player.Item;
import Player.Player;

import javax.swing.*;
import java.awt.*;

public class Robber extends Item {

    JLabel robberDisplay;
    Hexagon currentHex;

    public Robber(JPanel inPanel) {
        super(null);
        addFilePath("./CatanPNGs/Robber.png");
        robberDisplay = new JLabel(new ImageIcon(new ImageIcon(getFilePath()).getImage().getScaledInstance(20, 40, Image.SCALE_DEFAULT)));
        inPanel.add(robberDisplay);
        robberDisplay.setBounds(20,20,20,40);
    }

    public void moveTo(Hexagon foundHex) {
        if(currentHex!=null){currentHex.removeRobber();}
        foundHex.activateRobber();
        moveRobber(foundHex.getCenterX(),foundHex.getCenterY());
    }

    private void moveRobber(int x, int y){
        robberDisplay.setBounds(x-10,y-10,20,20);
    }
}
