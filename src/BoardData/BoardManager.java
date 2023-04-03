package BoardData;

import Player.Player;
import Player.Item;
import Universal.Catan; //for the resource stuff (maybe extract this)
import Universal.Dice;


import java.util.*;

import static Universal.Catan.Resource.*;
import static java.lang.Math.*;

public class BoardManager {
    //For keeping track of vertices, edges, and hexagons
        //Perfectly fine to index here with negative values...
    public ArrayList<Player> players;
    public ArrayList<ArrayList<Hexagon>> numberToTile;
    public Dice dice;
    public  Hexagon[][] hMap;

    public Queue<Item> placementOnClickQueue;

    boolean gameStarted;

    public BoardManager() {
        //Initialize the 2d ArrayList
        numberToTile = new ArrayList<>();
        for(int i = 0; i<13;i++){
            numberToTile.add(new ArrayList<>());
        }

        gameStarted = false;
        dice = new Dice();
        placementOnClickQueue = new ArrayDeque<Item>();
        buildCatanBoard();
    }

    public void buildCatanBoard(){
        Random rng = new Random();
        ArrayList<Catan.Resource> resources = new ArrayList<>(Arrays.asList(DESERT,WOOD,WOOD,WOOD,WOOD,GRAIN,GRAIN,GRAIN,GRAIN,WOOL,WOOL,WOOL,WOOL,CLAY,CLAY,CLAY,ORE,ORE,ORE));
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11));
        hMap = new Hexagon[5][7];

        Catan.Resource resource;
        for(int j=0; j<5; j++){   //Column (maxIndex is made by j+2)
            for(int i=max(j-2,0); i<=min(j+2,4); i++){
                resource = resources.remove(rng.nextInt(resources.size()));
                hMap[j][i] = new Hexagon(resource);

                //TODO: REMOVE
                hMap[j][i].setCenterX(i);
                hMap[j][i].setCenterY(j);
            }
        }

        //loops through the middle circle of hexes
        //ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(2,1,3,2,3,3,2,3,1,2,1,1));
        ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(3,2,2,1,1,1,1,2,2,3,3,3));
        for (int temp1 = 0; temp1<12; temp1+=2) {
            int j = indices.get(temp1);
            int i = indices.get(temp1 + 1);

            //Division by 2 passed as an integer should automatically floor for positive numbers
            hMap[j][i].radius1Attachment(temp1/2, new Hexagon[]{hMap[j - 1][i], hMap[j][i + 1],
                    hMap[j + 1][i + 1], hMap[j + 1][i],
                    hMap[j][i - 1], hMap[j - 1][i - 1]});
        }


        //Map all the individual ports manually
        hMap[0][0].edges[5].setPort(new Port(DESERT));
        hMap[0][1].edges[0].setPort(new Port(GRAIN));
        hMap[1][3].edges[0].setPort(new Port(ORE));
        hMap[2][4].edges[1].setPort(new Port(DESERT));
        hMap[3][4].edges[2].setPort(new Port(WOOL));
        hMap[4][3].edges[2].setPort(new Port(DESERT));
        hMap[4][2].edges[3].setPort(new Port(DESERT));
        hMap[3][1].edges[4].setPort(new Port(CLAY));
        hMap[1][0].edges[4].setPort(new Port(GRAIN));

        //Finally, number the hexes
        Hexagon nextHex = hMap[0][0];
        int dir = 1;
        while(!numberList.isEmpty()) {
            if (nextHex.resource == DESERT) {
                nextHex.setTokenNum(1);
            }else{
                int num = numberList.remove(0);
                nextHex.setTokenNum(num);
                numberToTile.get(num).add(nextHex);
            }

            try {
                dir = nextHex.getNextTokenlessHex(dir);
                nextHex = nextHex.adjacentHexes[dir];
            } catch (IndexOutOfBoundsException e) {   //Once there are no more to number, we are done
                break;
            }
        }
    }

    public Player getCurrentPlayer(){
        return players.get(0);
    }

    public ArrayList<Player> getOtherPlayers() {
        return new ArrayList<>(players.subList(1,players.size()));
    }


    public void addToPlacementQueue(Item item){
        placementOnClickQueue.add(item);
    }

    public Item peekPlacementQueue(){
       return placementOnClickQueue.peek();
    }

    public Item removeFirstFromQueue(){
        return placementOnClickQueue.remove();
    }

    public void clearPlacementQueue(){
        placementOnClickQueue.clear();
    }

    public boolean didGameBegin(){
        return gameStarted;
    }

    public void setGameBegin(Boolean bool){
        gameStarted = bool;
        if(bool){
            giveStartingResources();
        }
    }

    private void giveStartingResources() {
        //TODO: This
    }


}
