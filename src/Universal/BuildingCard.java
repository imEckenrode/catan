package Universal;

import BoardData.BoardManager;
import Player.Recipe;
import Player.Settlement;
import Player.Road;
import Player.Player;

import java.util.ArrayList;

public class BuildingCard {
    //We really only need one of these that changes colors depending on whose turn it is
    ArrayList<Recipe> recipes;

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
