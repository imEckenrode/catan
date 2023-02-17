package BoardData;
import Player.Player;
import java.awt.*;

public class Edge {
    Port port;
    EdgeItem item;

    public Edge(){
        port = null;
        item = null;
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
}
