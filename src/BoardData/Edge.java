package BoardData;
import GUI.PlaceableItem;
import Player.Road;
import Player.Settlement;

import javax.swing.*;

public class Edge extends PlaceableItem {
    private static final int X_SHIFT = 5;   //This constant makes sure the edges are displayed correctly
    private static final int[] EDGE_CENTERS_X = new int[]{25,50,25,-25,-50,-25};
    private static final int[] EDGE_CENTERS_Y = new int[]{-37,0,37,37,0,-37};
    Port port;
    Road road;

    int tilt; //   0 = \, 1 = |, 2 = /

    public Edge(int dir){
        super(null,EDGE_CENTERS_X[dir], EDGE_CENTERS_Y[dir],40,40);
        port = null;
        road = null;
        tilt = dir%3;
    }

    public Edge(Port port, Road road) {
        this.port = port;
        this.road = road;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
        setImageFile(road.getFilePath(0));
    }

    /*
    public void setRoad(Road road, int imagePick) {
        this.road = road;
        setImageFile(road.getFilePath(dir));
    }   */

    public void setCenterX(int centerX, int dir) {
        super.setCenterX(centerX + EDGE_CENTERS_X[dir]);
    }

    public void setCenterY(int centerY, int dir) {
        super.setCenterY(centerY + EDGE_CENTERS_Y[dir]);
    }


    public void setRoad(Road road, JPanel itemsPanel) {
        //There is no case where a settlement will be removed from the board.
        //This makes sure we do not honor the attempt
        if(road == null){
            System.out.println("Bad settlement added");
            return;
        }
        this.road = road;
        setImageFile(road.getFilePath(tilt));
        drawImage(itemsPanel, road.getColor());
    }
    public Port getPort(){
        return port;
    }
    public void setPort(Port port) {
        this.port = port;
    }
    public void removePort(){
        port = null;
    }

    public int getTilt() {
        return tilt;
    }

    public void setTilt(int tilt) {
        this.tilt = tilt;
    }
}
