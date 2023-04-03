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
}
