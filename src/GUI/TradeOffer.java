package GUI;

import Player.Player;
import Universal.Catan;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;

public class TradeOffer extends JDialog {
    private JPanel contentPane;
    private JButton buttonAccept;
    private JButton buttonDeny;
    private JButton exposeCurrentResourceCountsButton;
    private JLabel tradeDescLabel;
    private JLabel tradeReqLabel;
    private JPanel acceptButtonsPanel;

    boolean accepted;
    public TradeOffer(Player accepting, Player asking, Catan.Resource taking, Catan.Resource giving) {
        accepted = false;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAccept);

        contentPane.setBorder(BorderFactory.createLineBorder(accepting.getColor(),10));

        acceptButtonsPanel.setBackground(asking.getColor());
        tradeDescLabel.setText("Want 1 "+taking.toString()+" for 1 "+giving.toString()+".");

        buttonAccept.addActionListener(e ->{
            if(accepting.hasEnoughResources(taking,1)){
                accepted = true;
                dispose();
            }else{
                tradeReqLabel.setText("Sorry, you do not have enough resources for this trade");
            }
        });

        buttonDeny.addActionListener(e -> onDeny());

        exposeCurrentResourceCountsButton.addActionListener(e->{
            ResourcePicker picker = new ResourcePicker(accepting, 0);
            picker.showDialog();
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onDeny();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onDeny(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onAccept() {

    }

    private void onDeny() {
        accepted = false;
        dispose();
    }

    public boolean showDialog() {
        //setSize(875,315);
        setSize(400,300);
        setVisible(true);
        return accepted;
    }

}
