package BoardData;

import Player.Player;
import Universal.Catan;

public class VertexItem {

    Player owner;

    public VertexItem() {
        this.owner = null;
    }
    public VertexItem(Player owner) {
        this.owner = owner;
    }

    public void collectResource(Catan.Resource resource) {
        if(owner != null){
            owner.getHand();    //TODO: This will give a new resource card
        }
    }
}
