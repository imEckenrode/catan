package BoardData;


import Universal.Catan;

import java.util.ArrayList;
import java.util.List;

public class Hexagon {
    ResourceHex resourceHex;

    Edge[] edges;
    Vertex[] vertices;


    public void generateAllSides(){
        for(int i = 0; i<6;i++){
            edges[i] = new Edge();
            vertices[i] = new Vertex();
        }
    }

    public void attachThree(Hexagon hexR, Hexagon hexDR, Hexagon hexDL){
        if(hexR != null){
            //Attach it in here, and do the same for the next two
        }
    }

    public Hexagon() {
        generateAllSides();
    }

    public Hexagon(ResourceHex resourceHex) {
        this.resourceHex = resourceHex;
    }

    public ResourceHex getResourceHex() {
        return resourceHex;
    }

    public void setResourceHex(ResourceHex resourceHex) {
        this.resourceHex = resourceHex;
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

    public boolean setEdge(int index, Vertex newVertex){
        vertices[index%6] = newVertex;
        return true;
    }
}
