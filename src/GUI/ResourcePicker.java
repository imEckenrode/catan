package GUI;

import Universal.Catan;
import Player.Player;
import Player.Hand;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ResourcePicker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private ButtonGroup resourceButtonGroup;
    private Catan.Resource pickedResource;
    ArrayList<Integer> currentResourceCount;

    public ResourcePicker(Player player, int resourcesRequired){
        if(player!=null) {
            currentResourceCount = player.getHand().getAllResourceCounts();
        }

        pickedResource = null;

        //TODO: Size up to also show brick'
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        resourceButtonGroup = new ButtonGroup();

        if(currentResourceCount==null){
            for(Catan.Resource r: Catan.Resource.values()){
                if(r==Catan.Resource.DESERT){continue;}//TODO: Would prefer string underneath?
                JRadioButtonMenuItem full = new JRadioButtonMenuItem(makeCardIcon(r.getCardFilePath()));
                mainPanel.add(full);
                resourceButtonGroup.add(full);
            }
        }else{
            for(Catan.Resource r: Catan.Resource.values()){
                if(r==Catan.Resource.DESERT){continue;}//TODO: Would prefer string underneath?
                JRadioButtonMenuItem full = new JRadioButtonMenuItem(r.getName()+": "+currentResourceCount.get(r.toIndex()),makeCardIcon(r.getCardFilePath()));
                mainPanel.add(full);
                resourceButtonGroup.add(full);
            }
        }

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

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
        int i = 0;
        for (Enumeration<AbstractButton> buttons = resourceButtonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                //System.out.println(button.getText());
                pickedResource = Catan.Resource.values()[i];
                dispose();
            }   //TODO: return the selected option

            i++;    //NOTE: THIS IS DEPENDENT ON THE ORDER OF THE RESOURCES
            //Catan.Resource selected = resourceButtonGroup.isSelected();
        }
        dispose();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ResourcePicker dialog = new ResourcePicker(null, 0);
        dialog.pack();
        System.out.println(dialog.showDialog());
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

    public Catan.Resource showDialog() {
        setVisible(true);
        return pickedResource;
    }
}
