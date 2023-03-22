package BoardData;

import Player.Player;
import Universal.Catan;

public class VertexItem {

    protected Player owner;

    public VertexItem() {
        this.owner = null;
    }
    public VertexItem(Player owner) {
        this.owner = owner;
    }

    public void collectResource(Catan.Resource resource) {
        return;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
