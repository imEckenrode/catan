package BoardData;

import GUI.PlaceableItem;

import Player.Player;
import Universal.Catan;
import Player.Settlement;

public class Vertex extends PlaceableItem{
    private static final int X_SHIFT = 15;   //This constant makes sure the vertices are displayed correctly
    private static final int[] VERTEX_CENTERS_X = new int[]{0,50,50,0,-50,-50};
    private static final int[] VERTEX_CENTERS_Y = new int[]{-50,-25,25,50,25,-25};
    Settlement settlement;


    public Vertex() {
        super(null,0,0,0,0);
        settlement = null;

    }

    public Vertex(int dir) {
        super(null,VERTEX_CENTERS_X[dir],VERTEX_CENTERS_Y[dir],0,0);
        settlement = null;
    }

    public void adjustCenterX(int changeInX) {
        super.setCenterX(getCenterX()+changeInX);
    }

    public void adjustCenterY(int changeInY) {
        super.setCenterY(getCenterY()+changeInY);
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }
}
