package BoardData;

import Universal.Catan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static Universal.Catan.Resource.*;
import static java.lang.Math.*;

public class BoardManager {
    //For keeping track of vertices, edges, and hexagons
        //Perfectly fine to index here with negative values...

    Hexagon[][] hMap;

    ArrayList<ArrayList<Hexagon>> numberToTile;

    public BoardManager() {
        //Initialize the 2d ArrayList
        numberToTile = new ArrayList<>();
        for(int i = 0; i<13;i++){
            numberToTile.add(new ArrayList<>());
        }

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
            }
        }
        //loops through the middle circle of hexes
        ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(2,1,3,2,3,3,2,3,1,2,1,1));
        for (int temp1 = 0; temp1<12; temp1+=2) {
            int j = indices.get(temp1);
            int i = indices.get(temp1 + 1);

            //Division by 2 passed as an integer should automatically floor for positive numbers
            hMap[j][i].radius1Attachment(temp1/2, new Hexagon[]{hMap[j - 1][i], hMap[j][i + 1],
                    hMap[j + 1][i + 1], hMap[j + 1][i],
                    hMap[j][i - 1], hMap[j - 1][i - 1]});

        }

        //TODO: Ports (DO AS BELOW, MOST LIKELY)
        //hMap[0][0].edges[5].setPort(new Port(DESERT));

        Hexagon nextHex = hMap[0][0];
        int dir = 1;
        while(!numberList.isEmpty()) {
            if (nextHex.resource != DESERT) {
                int num = numberList.remove(0);
                nextHex.setTokenNum(num);
                numberToTile.get(num).add(nextHex);
            }
            try {
                nextHex = nextHex.getNextTokenlessHex(dir);
            } catch (IndexOutOfBoundsException e) {   //Once there are no more to number, we are done
                break;
            }
        }
    }
}
