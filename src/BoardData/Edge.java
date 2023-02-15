package BoardData;
import Player.Player;
import java.awt.*;

public class Edge {
    boolean isPort;
    EdgeItem item;

    public Edge(boolean isPort, EdgeItem item) {
        this.isPort = isPort;
        this.item = item;
    }
}
