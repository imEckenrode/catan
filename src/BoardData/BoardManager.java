package BoardData;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class BoardManager {
    //For keeping track of vertices, edges, and hexagons
        //Perfectly fine to index here with negative values...

    Hexagon[][] hMap;

    ArrayList<ArrayList<Hexagon>> numberToTile;
    int[] number = new int[]{5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11};
        //Remember to skip the desert though, so assign resources to hex's as we go.
        //May need to reverse order of tilings so we can pop out as we go
    public BoardManager() {
        hMap = new Hexagon[5][7];
        for(int j=0; j<5; j++){   //Column (maxIndex is made by j+2)
            for(int i=max(j-2,0); i<=min(j+2,4); i++){       //Generates one more out to allow for easy indexing with the connection functions (otherwise do try catch)
                //If the tile is on the board, create it.
                hMap[j][i] = new Hexagon();
                //For an arrayList, would need fullMap.get(j).set(i, abs(j-i)>2 ? new Hexagon() : null);
                //System.out.println(abs(j-i));
            }
        }
        //Now generate connections for 11,12,23,33,32,21 (the r=1 ring)
        //for(int j=0; j<5; j++){   //Column (maxIndex is made by j+2)
            //for(int i=max(j-2,0); i<=min(j+2,4); i++){   //This remains within the board

        int j = 1;
        int i = 1;
                 hMap[j][i].radius1Attachment(2, new Hexagon[]{hMap[j - 1][i], hMap[j][i + 1],
                                                                        hMap[j + 1][i + 1], hMap[j + 1][i],
                                                                        hMap[j][i - 1],hMap[j - 1][i - 1]});

                 //new Hexagon[]{hMap[j - 1][i]}

                System.out.println(hMap[j + 1][i+1].edges[5]);
                System.out.println(hMap[j][i].edges[2]);
            //}
        //}

    }
}
