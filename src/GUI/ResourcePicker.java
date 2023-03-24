package GUI;

import Universal.Catan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class ResourcePicker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private ButtonGroup resourceButtonGroup;

    public ResourcePicker() {
        //TODO: Size up to also show brick'
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        resourceButtonGroup = new ButtonGroup();

        for(Catan.Resource r: Catan.Resource.values()){
            System.out.println(r);
            if(r==Catan.Resource.DESERT){continue;}//TODO: Would prefer string underneath?
            JRadioButtonMenuItem full = new JRadioButtonMenuItem(r.toString(), makeCardIcon(r.getCardFilePath()));
            mainPanel.add(full);
            resourceButtonGroup.add(full);
        }

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

    private Catan.Resource onOK() {
        int i = 0;
        for (Enumeration<AbstractButton> buttons = resourceButtonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                System.out.println(button.getText());
                dispose();
                return(Catan.Resource.values()[i]);
            }   //TODO: return the selected option
            //Catan.Resource selected = resourceButtonGroup.isSelected();
        }
        dispose();
        return(null);
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ResourcePicker dialog = new ResourcePicker();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public ImageIcon makeCardIcon(String file){
        BufferedImage tempImage;
        try {
            tempImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ImageIcon(tempImage);//new ImageIcon(tempImage).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT))));
    }
}
