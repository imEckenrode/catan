package Player;

import BoardData.VertexItem;
import Universal.Catan;

public class Settlement extends VertexItem{
    //Could just implement isCity as a boolean in here and change the display and resources given based on that
    boolean isCity;


    public Settlement(Player owner) {
        super(owner);
        this.isCity = false;
    }
    public Settlement(Player owner, boolean isCity) {
        super(owner);
        this.isCity = isCity;
    }

    @Override
    public void collectResource(Catan.Resource resource) {
        if(owner != null){
            owner.getHand().addResource(resource);
            if(isCity){
                owner.getHand().addResource(resource);
            }
        }
    }
}
