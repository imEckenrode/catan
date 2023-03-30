package BoardData;
import GUI.PlaceableItem;
import Player.Road;

public class Edge extends PlaceableItem {
    private static final int X_SHIFT = 5;   //This constant makes sure the edges are displayed correctly
    private static final int[] EDGE_CENTERS_X = new int[]{25,50,25,-25,-50,-25};
    private static final int[] EDGE_CENTERS_Y = new int[]{-37,0,37,37,0,-37};
    Port port;
    Road road;

    int tilt; //   0 = \, 1 = |, 2 = /

    public Edge(){
        super(null,0,0,25,25);
        port = null;
        road = null;
    }

    public Edge(int dir){
        super(null,EDGE_CENTERS_X[dir], EDGE_CENTERS_Y[dir],25,25);
        port = null;
        road = null;
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
        super.setCenterX(getCenterX() + changeInX);
    }
    public void adjustCenterY(int changeInY) {
        super.setCenterY(getCenterY() + changeInY);
    }

}
