package Player;
import GUI.PlaceableItem;
import Universal.Catan;

public class Settlement{
    //Could just implement isCity as a boolean in here and change the display and resources given based on that
    boolean isCity;
    Player owner;

    public Settlement(Player owner) {
        this.owner = owner;
        this.isCity = false;
    }

    public Settlement(Player owner, boolean isCity) {
        this.owner = owner;
        this.isCity = isCity;
    }

    public void collectResource(Catan.Resource resource) {
        if(owner != null){
            owner.getHand().addResource(resource);
            if(isCity){
                owner.getHand().addResource(resource);
            }
        }
    }
}
