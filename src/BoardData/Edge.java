package BoardData;
import Player.Player;
import java.awt.*;

public class Edge {
    private static final int[] EDGE_CENTERS_X = new int[]{25,50,25,-25,-50,-25};
    private static final int[] EDGE_CENTERS_Y = new int[]{-37,0,37,37,0,-37};
    Port port;
    EdgeItem item;
    int centerX;
    int centerY;

    public Edge(){
        port = null;
        item = null;
        centerX = 0;
        centerY = 0;
    }

    public Edge(int dir){
        port = null;
        item = null;
        centerX = EDGE_CENTERS_X[dir];
        centerY = EDGE_CENTERS_Y[dir];
    }

    public Edge(Port port, EdgeItem item) {
        this.port = port;
        this.item = item;
    }

    public EdgeItem getItem() {
        return item;
    }

    public void setItem(EdgeItem item) {
        this.item = item;
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

    public void adjustCenterX(int changeInX) {
        centerX += changeInX;
    }
    public void adjustCenterY(int changeInY) {
        centerY += changeInY;
    }

}
