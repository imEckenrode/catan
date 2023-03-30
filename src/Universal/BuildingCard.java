package Universal;

import BoardData.BoardManager;
import GUI.PlaceableItem;
import Player.Settlement;
import Player.Road;
import Player.Player;

public class BuildingCard extends PlaceableItem {
    //We really only need one of these that changes colors depending on whose turn it is

    public BuildingCard(String imageFile, int centerX, int centerY, int height, int width) {
        super(imageFile, centerX, centerY, height, width);
    }

    public void buyThing(Player player, BoardManager model, int i){
        switch(i){
            case 0:
                //Buy a settlement
                model.addToPlacementQueue(new Settlement(player));
                break;
            case 1:
                model.addToPlacementQueue(new Road(player));
                break;
            case 2:
                //TODO: Find a way to upgrade from settlement to city
                break;
        }
    }
}
