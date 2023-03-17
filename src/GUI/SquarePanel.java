package GUI;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    @Override
    public Dimension getPreferredSize() { //Forces JPanel to be a Square :)

        Dimension d = getParent().getSize();

        int x = Math.min((int) d.getWidth(), (int) d.getHeight());
        return new Dimension(x, x);
    }
    @Override
    public void paintComponent(Graphics g){
       Graphics2D g2d = (Graphics2D)g;
        Image background = Toolkit.getDefaultToolkit().createImage("FullBackGround.png");
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
