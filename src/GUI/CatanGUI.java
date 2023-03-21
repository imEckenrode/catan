package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CatanGUI {
    private JPanel OtherHandsPanel;
    private JPanel Hand1Panel;
    private JPanel Hand2Panel;
    private JPanel Hand3Panel;
    private JPanel TablePanel;
    private JPanel CurrentHandPanel;
    private JPanel BuildingCardPanel;
    private JPanel PortsPanel;
    private JPanel ActionsPanel;
    private JPanel CatanPanel;
    private JPanel FourForOne;
    private JPanel ToDoAdd6;
    private JPanel BoardPanel;
    public void main(String[] arguments) throws IOException {
        BufferedImage image = ImageIO.read(new File(".FullBackGround.jpg"));
        JLabel label = new JLabel(new ImageIcon(image));
        BoardPanel.add(label);
    }
    private void createUIComponents() {
    }
}
