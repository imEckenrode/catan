package Player;

import Universal.Catan;
import Universal.Dice;

import java.util.*;

public class Hand {
    ArrayList<Integer> resourceCards;
    public Hand() {
        this.resourceCards = new ArrayList<>(Arrays.asList(0,0,0,0,0));
    }

    public void addResource(Catan.Resource resource) {
        if(resource == null){return;}
        int index = resource.toIndex();
        resourceCards.set(index, resourceCards.get(index)+1);
    }

    public void removeResource(Catan.Resource resource) {
        if(resource == null){return;}
        int index = resource.toIndex();
            if (resourceCards.get(index) > 0) {
                resourceCards.set(index, resourceCards.get(index) - 1);
            } else {
                throw new RuntimeException();
            }
    }

    public void removeResource(Catan.Resource resource, int number) {
        if(resource == null){return;}
        int index = resource.toIndex();
        for (int i = 0; i < number; i++) {
            if (resourceCards.get(index) > 0) {
                resourceCards.set(index, resourceCards.get(index) - 1);
            } else {
                throw new RuntimeException();
            }
        }
    }

    public void randomLoseHalf(Dice dice){
        for(int i = 0; i<=totalResourceCount()/2; i++){
            removeRandomResource(dice);
        }
    }

    public Catan.Resource removeRandomResource(Dice dice){
       int lostNum = dice.randomInt(totalResourceCount());
       for(Catan.Resource r: Catan.Resource.values()){
            lostNum -= resourceCards.get(r.toIndex());
            if(lostNum<=0){
                removeResource(r);
                return r;
            }
       }
       System.out.println("Error: Could not return resource, defaulting to wood.");
       return Catan.Resource.WOOD;
    }

    public void setResourceCount(Catan.Resource resource, int number) {
        int index = resource.toIndex();
        resourceCards.set(index, number);
    }

    public int getResourceCount(Catan.Resource resource){
        return resourceCards.get(resource.toIndex());
    }

    public ArrayList<Integer> getAllResourceCounts() {
        return resourceCards;
    }

    public int totalResourceCount(){
        int total = 0;
        for(int i: getAllResourceCounts()){
            total+=i;
        }
        return total;
    }

    public boolean hasResource(Catan.Resource resource, int i) {
        if (resourceCards.get(resource.toIndex())>=i){
            return true;
        }
        return false;
    }
}
