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

    ArrayList resources;
    ArrayList<ArrayList<Hexagon>> numberToTile;
    int[] number = new int[]{5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11}; //TODO: assign numbers to hexes
        //Remember to skip the desert though, so assign resources to hex's as we go.
        //May need to reverse order of tilings so we can pop out as we go
    public BoardManager() {
        Random rng = new Random();
        resources = new ArrayList<>(Arrays.asList(DESERT,WOOD,WOOD,WOOD,WOOD,GRAIN,GRAIN,GRAIN,GRAIN,WOOL,WOOL,WOOL,WOOL,CLAY,CLAY,CLAY,ORE,ORE,ORE));

        hMap = new Hexagon[5][7];
        Catan.Resource resource = null;
        for(int j=0; j<5; j++){   //Column (maxIndex is made by j+2)
            for(int i=max(j-2,0); i<=min(j+2,4); i++){
                resource = (Catan.Resource) resources.remove(rng.nextInt(resources.size()));
                hMap[j][i] = new Hexagon(resource);
            }
        }

        int j = 1;  //TODO: Select all 6 of the r=1 tiles efficiently...or just have the below list to 2,2
        int i = 1;
                 hMap[j][i].radius1Attachment(2, new Hexagon[]{hMap[j - 1][i], hMap[j][i + 1],
                                                                        hMap[j + 1][i + 1], hMap[j + 1][i],
                                                                        hMap[j][i - 1],hMap[j - 1][i - 1]});

                System.out.println(hMap[j + 1][i+1].edges[5]);
                System.out.println(hMap[j][i].edges[2]);
            //}
        //}

        //TODO: Ports and Numbering (do them at the same time)
        hMap[0][0].edges[5].setPort(new Port(DESERT));
    }
}
