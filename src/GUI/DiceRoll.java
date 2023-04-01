package GUI;

import Universal.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DiceRoll extends JDialog {
    public static final String[] die = {"⚀", "⚁", "⚂","⚃", "⚄", "⚅"};
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel insidePane;
    private JLabel totalLabel;
    private JLabel diceFaceLabel;
    int result;

    public DiceRoll(Dice dice) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        ArrayList<Integer> both = dice.rollDiceSeparately();
        result = both.get(0)+both.get(1);
        if(result == 7){
            totalLabel.setText("Robber Event!");
        }else {
            totalLabel.setText("You rolled " + result + "!");
        }

        totalLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        diceFaceLabel.setText(die[both.get(0)-1]+die[both.get(1)-1]);
        diceFaceLabel.setFont(new Font("Serif", Font.PLAIN, 72));


        //insidePane.add(totalLabel);
        //insidePane.add(diceFaceLabel);

        buttonOK.addActionListener(e -> onOK());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public int showDialog() {
        //setSize(875,315);
        setSize(400,300);
        //TODO: Could create an actual label instead of using radio button's built-in label, then go back to previous size
        setVisible(true);
        return result;
    }

    public static void main(String[] args) {
        DiceRoll dialog = new DiceRoll(new Dice());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
