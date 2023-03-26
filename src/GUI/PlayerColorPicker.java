package GUI;

import Universal.Catan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerColorPicker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JColorChooser colorChooser;
    Color pickedColor;

    public PlayerColorPicker() {
        pickedColor = null;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

    private void onOK() {
        pickedColor = colorChooser.getColor();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Color showDialog() {
        pack();
        //setSize(600,600);
        setVisible(true);
        return pickedColor;
    }

    public static void main(String[] args) {
        PlayerColorPicker dialog = new PlayerColorPicker();
        System.out.println(dialog.showDialog());
        System.exit(0);
    }
}
