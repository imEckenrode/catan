package Player;

import Universal.Catan;

import java.util.*;

public class Hand {
    ArrayList<Integer> resourceCards;
    public Hand() {
        this.resourceCards = new ArrayList<>(Arrays.asList(0,0,0,0,0));
    }

    public void addResource(Catan.Resource resource) {
        int index = resource.toIndex();
        resourceCards.set(index, resourceCards.get(index)+1);
    }

    public void setResourceCount(Catan.Resource resource, int number) {
        int index = resource.toIndex();
        resourceCards.set(index, number);
    }

    public int getResourceCount(Catan.Resource resource){
        return resourceCards.get(resource.toIndex());
    }
}
