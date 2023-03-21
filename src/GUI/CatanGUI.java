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
    private JPanel gridPanel;


    private void createUIComponents() {

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

    public void setTablePanel(JPanel tablePanel) {
        TablePanel = tablePanel;
    }

    public JPanel getCurrentHandPanel() {
        return CurrentHandPanel;
    }

    public void setCurrentHandPanel(JPanel currentHandPanel) {
        CurrentHandPanel = currentHandPanel;
    }

    public JPanel getBuildingCardPanel() {
        return BuildingCardPanel;
    }

    public void setBuildingCardPanel(JPanel buildingCardPanel) {
        BuildingCardPanel = buildingCardPanel;
    }

    public JPanel getPortsPanel() {
        return PortsPanel;
    }

    public void setPortsPanel(JPanel portsPanel) {
        PortsPanel = portsPanel;
    }

    public JPanel getActionsPanel() {
        return ActionsPanel;
    }

    public void setActionsPanel(JPanel actionsPanel) {
        ActionsPanel = actionsPanel;
    }

    public void setCatanPanel(JPanel catanPanel) {
        CatanPanel = catanPanel;
    }

    public JPanel getFourForOne() {
        return FourForOne;
    }

    public void setFourForOne(JPanel fourForOne) {
        FourForOne = fourForOne;
    }

    public JPanel getToDoAdd6() {
        return ToDoAdd6;
    }

    public void setToDoAdd6(JPanel toDoAdd6) {
        ToDoAdd6 = toDoAdd6;
    }

    public JPanel getBoardPanel() {
        return BoardPanel;
    }

    public void setBoardPanel(JPanel boardPanel) {
        BoardPanel = boardPanel;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }
}
