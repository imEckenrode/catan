package Universal;

import BoardData.BoardManager;
import Player.Recipe;
import Player.Settlement;
import Player.Player;

import java.util.ArrayList;

public class BuildingCard {
    //We really only need one of these that changes colors depending on whose turn it is
    ArrayList<Recipe> recipes;

    public void buyThing(Player player, BoardManager model, int i){
        switch(i){
            case 0:
                //Buy a settlement
                model.addToQueue(new Settlement(player));
        }
    }
}
