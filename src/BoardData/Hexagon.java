package BoardData;


import Universal.Catan;

import java.util.ArrayList;
import java.util.List;

public class Hexagon {
    Catan.Resource resource;
    public Edge[] edges;
    public Vertex[] vertices;

    int tokenNum;
    boolean hasRobber;


    public void generateAllSides(){
        edges = new Edge[6];
        vertices = new Vertex[6];
        for(int i = 0; i<6;i++){
            edges[i] = new Edge();
            vertices[i] = new Vertex();
        }
    }

    public void radius1Attachment(int dir, Hexagon[] hexes){
        if(dir>6 || dir<1){
            dir = 6;    //Keep orientation within the range, don't even accept outside
        }

        //Connect (almost) all edges directly attached to hexagon
                // Have to be careful here, add 5 to the orientation to avoid negatives
        for(int currentDir = dir; currentDir != (dir+5)%6; currentDir=(currentDir+1)%6) {
            hexes[currentDir].setEdge((currentDir + 3) % 6, edges[currentDir]);
        }
        for(int currentDir = (dir+1)%6; currentDir != (dir+4)%6; currentDir=(currentDir+1)%6){
            hexes[currentDir].setVertex((currentDir+4)%6, vertices[currentDir]);
            hexes[(currentDir+5)%6].setVertex((currentDir+2)%6, vertices[currentDir]);
        }

        //Now connect the needed edges and vertices for the 2nd ring of hexagons, with dir + 3 providing the objects
        hexes[(dir+2)%6].setEdge((dir+4)%6, hexes[(dir + 3)%6].edges[(dir+1)%6]);
        hexes[(dir+2)%6].setVertex((dir+4)%6, hexes[(dir + 3)%6].vertices[(dir+2)%6]);

        hexes[(dir+4)%6].setEdge((dir+2)%6, hexes[(dir + 3)%6].edges[(dir+5)%6]);
        hexes[(dir+4)%6].setVertex((dir+3)%6, hexes[(dir + 3)%6].vertices[(dir+2)%6]);
            //Add one to the right-hex vertex index to align the correct vertex

    }   //Orientation is what side of the hexagon is the middle

    public Hexagon() {
        generateAllSides();
    }

    public Hexagon(Catan.Resource resource) {
        this.resource = resource;
        generateAllSides();
    }

    public Catan.Resource getResourceType() {
        return resource;
    }

    public void setResourceType(Catan.Resource resource) {
        this.resource = resource;
    }

    public Edge getEdge(int index){
        return edges[index%6];  //Modulo 6 to guarantee the index is within the 6 possible
    }

    public boolean setEdge(int index, Edge newEdge){
        edges[index%6] = newEdge;
        return true;
    }

    public Vertex getVertex(int index){
        return vertices[index%6];
    }

    public boolean setVertex(int index, Vertex newVertex){
        vertices[index%6] = newVertex;
        return true;
    }
}
