package GUI;

import Player.Player;
import Universal.Catan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class PlayerPicker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private Player player;
    private ButtonGroup buttonGroup;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerPicker(ArrayList<Player> playerList) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonGroup = new ButtonGroup();

       for (Player i: playerList){
           JRadioButton button = new JRadioButton();
           button.setBackground(i.getColor());
           //button.setSize(200,200);
           System.out.println("test");
           mainPanel.add(button);
           buttonGroup.add(button);
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(playerList);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(ArrayList<Player> playerList) {
        int i = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                setPlayer(playerList.get(i));
            }
            i++;
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public Player showDialog() {
        //setSize(875,315);
        setSize(1150,300);
        setVisible(true);
        return player;
    }

    public static void main(String[] args){
        PlayerPicker dialog = new PlayerPicker(new ArrayList<Player>(Arrays.asList(new Player(Color.BLUE), new Player(Color.RED))));
        dialog.pack();
        System.out.println(dialog.showDialog());
        System.exit(0);
    }
}
