package GUI;

import Universal.Catan;

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
    private JButton endTurnButton;
    private JButton hand1Button;
    private JButton hand2Button;
    private JButton hand3Button;
    private JButton four2oneTradeButton;
    private JButton three2oneTradeButton;
    private JButton tradeButton0;
    private JButton tradeButton1;
    private JButton tradeButton2;
    private JButton tradeButton3;
    private JButton tradeButton4;
    private JPanel ItemsPanel;
    private JPanel BuildButtons;
    private JButton BuildRoadButton;
    private JButton BuildSettlentButton;
    private JButton BuildCityButton;
    private JLabel TempLabel;
    private JPanel gridPanel;

    public JButton getTradeButton(Catan.Resource r){
        //Could do this better by including all in a separate JButton[], but that's likely as much hardcoding
        switch (r.toIndex()){
            case -1:
                return three2oneTradeButton;
            case 0:
                return tradeButton0;
            case 1:
                return tradeButton1;
            case 2:
                return tradeButton2;
            case 3:
                return tradeButton3;
            case 4:
                return tradeButton4;
            default:
                return four2oneTradeButton;
        }//return portTradeButtons[r.toIndex()+1];
    }

    public JPanel getCatanPanel() {
        return CatanPanel;
    }

    public JPanel getOtherHandsPanel() {
        return OtherHandsPanel;
    }

    public JPanel getHand1Panel() {
        return Hand1Panel;
    }

    public JPanel getHand2Panel() {
        return Hand2Panel;
    }

    public JPanel getHand3Panel() {
        return Hand3Panel;
    }

    public JPanel getTablePanel() {
        return TablePanel;
    }

    public JPanel getCurrentHandPanel() {
        return CurrentHandPanel;
    }

    public JPanel getBuildingCardPanel() {
        return BuildingCardPanel;
    }

    public JPanel getPortsPanel() {
        return PortsPanel;
    }

    public JPanel getActionsPanel() {
        return ActionsPanel;
    }

    public JPanel getFourForOne() {
        return FourForOne;
    }

    public JPanel getToDoAdd6() {
        return ToDoAdd6;
    }

    public JPanel getBoardPanel() {
        return BoardPanel;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    public JButton getHand1Button() {
        return hand1Button;
    }

    public JButton getHand2Button() {
        return hand2Button;
    }

    public JButton getHand3Button() {
        return hand3Button;
    }

    public JButton getFour2oneTradeButton() {return four2oneTradeButton;}

    public JPanel getItemsPanel() {
        return ItemsPanel;
    }

    public JPanel getBuildButtons() {
        return BuildButtons;
    }
    public JButton getBuildRoadButton() {
        return BuildRoadButton;
    }
    public JButton getBuildSettlentButton() {
        return BuildSettlentButton;
    }
    public JButton getBuildCityButton() {
        return BuildCityButton;
    }
    public JLabel ButtonImage(String file,int width,int height){
            BufferedImage tempImage = null;
            try {
                tempImage = ImageIO.read(new File(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JLabel label = new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
            return label;
    }

}


