package Player;

import java.awt.*;

public class Player {
    Hand hand; //By abstracting this to a hand, we can have displayHand and loseHalf functions inside
    int victoryPoints;  //This could be an array!
    Color color;

    public Player(Hand hand, int victoryPoints, Color color) {
        this.hand = hand;
        this.victoryPoints = victoryPoints;
        this.color = color;
    }


    int settlements = 5;    //number of settlements the player has left
    int cities = 4;         //number of cities left
    int roads = 15;         // number of roads left
    public boolean SettlementsLeft(){   //function to check if the player has a settlement left to build
        settlements-=1;
        if (settlements <= 0) {
            return false;
        }
        return true;
    }
    public boolean CitiesLeft(){    //check for cities left
        cities-=1;
        if (cities <= 0) {
            return false;
        }
        settlements+=1; //add a settlement back because cities replace settlements
        return true;
    }
    public boolean RoadsLeft(){ //check for roads left
        roads-=1;
        if (roads <= 0) {
            return false;
        }
        return true;
    }
}

