package GUI;

import Universal.Catan;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class ResourcePicker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private ButtonGroup resourceButtonGroup;
    private Catan.Resource pickedResource;
    ArrayList<Integer> resourceCounts;
    int resourcesRequired;

    public ResourcePicker(){
        pickedResource = null;
        resourcesRequired = 0;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        resourceButtonGroup = new ButtonGroup();

        for(Catan.Resource r: Catan.Resource.values()){
            if(r==Catan.Resource.DESERT){continue;}//TODO: Would prefer string underneath?
            JRadioButtonMenuItem full = new JRadioButtonMenuItem(makeCardIcon(r.getCardFilePath()));
            mainPanel.add(full);
            resourceButtonGroup.add(full);
        }

        buttonOK.addActionListener(e -> {
            onOK();dispose();});

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public ResourcePicker(Player player, int resourcesRequired){
        this.resourcesRequired = resourcesRequired;
        if(player!=null) {
            resourceCounts = player.getHand().getAllResourceCounts();
        }else{
            resourceCounts = new ArrayList<>(Arrays.asList(0,0,0,0,0));
        }

        pickedResource = null;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        resourceButtonGroup = new ButtonGroup();

        for(Catan.Resource r: Catan.Resource.values()){
            if(r==Catan.Resource.DESERT){continue;}
            JRadioButtonMenuItem full = new JRadioButtonMenuItem("Have: "+ resourceCounts.get(r.toIndex()),makeCardIcon(r.getCardFilePath()));
            full.setEnabled(hasEnoughResources(r));
            mainPanel.add(full);
            resourceButtonGroup.add(full);
        }

        buttonOK.addActionListener(e -> {
            onOK();
            if(hasEnoughResources()){
                dispose();
            }
            pickedResource = null;
            System.out.println("Insufficient Resources");
            //TODO: Print this as an error message
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private boolean hasEnoughResources() {
        if(pickedResource==null){
            return false;
        }
        //Check if the player has enough resources
        return resourceCounts.get(pickedResource.toIndex()) >= resourcesRequired;
    }

    private boolean hasEnoughResources(Catan.Resource r) {
        if(r == null || r == Catan.Resource.DESERT){
            return false;
        }
        //Check if the player has enough resources
        return resourceCounts.get(r.toIndex()) >= resourcesRequired;
    }

    private void onOK() {
        int i = 0;
        for (Enumeration<AbstractButton> buttons = resourceButtonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                //System.out.println(button.getText());
                pickedResource = Catan.Resource.values()[i];
            }
            i++;    //NOTE: THIS IS DEPENDENT ON THE ORDER OF THE RESOURCES
            //Catan.Resource selected = resourceButtonGroup.isSelected();
        }
    }

    private void onCancel() {
        dispose();
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
        //setSize(875,315);
        setSize(1150,300);
        //TODO: Create an actual label instead of using radio button's built-in label, then go back to previous size
        setVisible(true);
        return pickedResource;
    }

    public static void main(String[] args) {
        ResourcePicker dialog = new ResourcePicker();
        dialog.pack();
        //dialog.s
        System.out.println(dialog.showDialog());
        System.exit(0);
    }
}
