package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PlaceableItem {
    String imageFile;
    int centerX;
    int centerY;
    int height;
    int width;

    public String getImageFile() {
        return imageFile;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public PlaceableItem(String imageFile, int centerX, int centerY, int height, int width) {
        this.imageFile = imageFile;
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
    }

    public PlaceableItem() {
        this.imageFile = null;
        this.centerX = 0;
        this.centerY = 0;
        height = 25;
        width = 25;
    }

    public void drawImage(JPanel panel){
            BufferedImage tempImage = null;
            try {
                tempImage = ImageIO.read(new File(imageFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JLabel label = new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
            panel.add(label);
            label.setBounds(centerX,centerY,width,height);
            label.setBounds((centerX-(int)(0.5*(width))),centerY,width,height);
    }

    public void drawImage(JPanel panel, Color playerColor){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int y = 0; y < tempImage.getHeight(); y++){
            for(int x = 0; x<tempImage.getWidth();x++){
                int pixel = tempImage.getRGB(x,y);
                Color color = new Color(pixel, true);
                if((pixel>>24) != 0x00){    //Check for transparency
                    tempImage.setRGB(x,y,playerColor.getRGB());
                }
            }
        }

        JLabel label = new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        panel.add(label);
        label.setBounds(centerX,centerY,width,height);
        label.setBounds((centerX-(int)(0.5*(width))),centerY,width,height);
    }

    public void drawImage(JPanel panel, int dir){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Color[] change = {Color.red,Color.orange,Color.yellow,Color.green,Color.blue,Color.pink};
        for(int y = 0; y < tempImage.getHeight(); y++){
            for(int x = 0; x<tempImage.getWidth();x++){
                int pixel = tempImage.getRGB(x,y);
                Color color = new Color(pixel, true);
                if((pixel>>24) != 0x00){    //Check for transparency
                    tempImage.setRGB(x,y,change[dir].getRGB());
                }
            }
        }

        JLabel label = new JLabel(new ImageIcon(new ImageIcon(tempImage).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        panel.add(label);
        label.setBounds(centerX,centerY,width,height);
        label.setBounds((centerX-(int)(0.5*(width))),centerY,width,height);
    }

    public double getAngle(int clickX, int clickY) {
        return Math.toDegrees(Math.atan2(clickY-centerY,clickX-centerX));
    }
}