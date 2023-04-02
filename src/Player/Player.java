package Player;

import BoardData.Port;
import Universal.Catan;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    Hand hand; //By abstracting this to a hand, we can have displayHand and loseHalf functions inside
    int victoryPoints;  //This could be an array!
    Color color;

    ArrayList<Port> portsOwned;

    public Player(Color color) {
        if(color==null){
            color = Color.WHITE;
        }
        this.color = color;
        hand = new Hand();
        victoryPoints = 0;
        portsOwned = new ArrayList<>();
    }

    public Player(Hand hand, int victoryPoints, Color color) {
        this.color = color;
        this.hand = hand;
        this.victoryPoints = victoryPoints;
        portsOwned = new ArrayList<>();
    }

    int settlementCount = 5;    //number of settlements the player has left
    int cityCount = 4;         //number of cities left
    int roadCount = 15;         // number of roads left
    public boolean SettlementLeftWithBuild(){   //function to check if the player has a settlement left to build
        settlementCount -=1;
        if (settlementCount <= 0) {
            return false;
        }
        return true;
    }
    public boolean CitiesLeftWithBuild(){    //check for cities left
        cityCount -=1;
        settlementCount +=1; //add a settlement back because cities replace settlements
        if (cityCount <= 0) {
            return false;
        }
        return true;
    }
    public boolean RoadsLeftWithBuild(){ //check for roads left
        roadCount -=1;
        if (roadCount <= 0) {
            return false;
        }
        return true;
    }

    public boolean hasEnoughResources(Catan.Resource r, int resourcesRequired) {
        if(r == null || r == Catan.Resource.DESERT){
            return false;
        }
        //Check if the player has enough resources
        return getHand().getResourceCount(r) >= resourcesRequired;
    }

    public Hand getHand() {
        return hand;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public boolean hasResourcePort(Catan.Resource resource){

        //TODO REMOVE THIS LINE
        return true;

//        for(Port p: portsOwned){
//            if(p.getResource() == resource){
//                return true;
//            }
//        }
//        return false;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void addVictoryPoint(){
        this.victoryPoints++;
    }
    public void removeVictoryPoint(){
        this.victoryPoints--;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) { //May also need to update settlements
        this.color = color;
    }

    public int getSettlementCount() {
        return settlementCount;
    }

    public void setSettlementCount(int settlements) {
        this.settlementCount = settlements;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cities) {
        this.cityCount = cities;
    }

    public int getRoadCount() {
        return roadCount;
    }

    public void setRoadCount(int roads) {
        this.roadCount = roads;
    }

    public void addPort(Port port){
        portsOwned.add(port);
    }

    public void raiseSettlementCount() {
        settlementCount++;
        victoryPoints--;
    }

    public void lowerCityCount() {
        cityCount--;
        victoryPoints++;
    }

    public void lowerSettlementCount() {
        settlementCount--;
        victoryPoints++;
    }

    public void lowerRoadCount() {
        roadCount--;
    }
}

