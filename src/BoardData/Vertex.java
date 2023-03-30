package BoardData;

import GUI.PlaceableItem;

import Player.Settlement;

import javax.swing.*;

public class Vertex extends PlaceableItem{
    private static final int[] VERTEX_CENTERS_X = new int[]{0,50,50,0,-50,-50};
    private static final int[] VERTEX_CENTERS_Y = new int[]{-50,-25,25,50,25,-25};
    Settlement settlement;


    public Vertex() {
        super(null,0,0,25,25);
        settlement = null;

    }

    public Vertex(int dir) {
        super(null,VERTEX_CENTERS_X[dir],VERTEX_CENTERS_Y[dir],25,25);
        settlement = null;
    }

    public void setCenterX(int centerX, int dir) {
        super.setCenterX(centerX + VERTEX_CENTERS_X[dir]);
    }

    public void setCenterY(int centerY, int dir) {
        super.setCenterY(centerY + VERTEX_CENTERS_Y[dir]);
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement, JPanel itemsPanel) {
        //There is no case where a settlement will be removed from the board.
        //This makes sure we do not honor the attempt
        if(settlement == null){
            System.out.println("Bad settlement added");
            return;
        }
        this.settlement = settlement;
        setImageFile(settlement.getFilePath());
        drawImage(itemsPanel);
    }
}
