package BoardData;

import GUI.PlaceableItem;

public class Vertex extends PlaceableItem {
    private static final int X_SHIFT = 15;   //This constant makes sure the vertices are displayed correctly
    private static final int[] VERTEX_CENTERS_X = new int[]{0,50,50,0,-50,-50};
    private static final int[] VERTEX_CENTERS_Y = new int[]{-50,-25,25,50,25,-25};
    VertexItem item;
    int centerX;
    int centerY;

    public Vertex() {
        item = null;
        centerX = 0;
        centerY = 0;
    }

    public Vertex(int dir) {
        item = null;
        centerX = VERTEX_CENTERS_X[dir] - X_SHIFT;
        centerY = VERTEX_CENTERS_Y[dir];
    }

    public void adjustCenterX(int changeInX) {
        centerX += changeInX;
    }

    public void adjustCenterY(int changeInY) {
        centerY += changeInY;
    }


}
