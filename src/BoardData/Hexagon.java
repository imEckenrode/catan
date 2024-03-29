package BoardData;


import GUI.PlaceableItem;
import Universal.Catan;
import Universal.Robber;

import javax.swing.*;
import java.util.Arrays;

//import static BoardData.Dir6.rot60;

public class Hexagon extends PlaceableItem {
    Catan.Resource resource;
    public Edge[] edges;
    public Vertex[] vertices;

    public Hexagon[] adjacentHexes;
    int tokenNum;
    boolean hasRobber;

    public Hexagon() {
        adjacentHexes = new Hexagon[6];
        generateAllSides();
    }

    public Hexagon(Catan.Resource resource) {
        super(resource.getHexFilePath(),0,0,100,100);
        this.resource = resource;
        adjacentHexes = new Hexagon[6];
        generateAllSides();
    }

    public boolean distributeResources() {
        if(hasRobber() || resource == Catan.Resource.DESERT){
            return false;   //Distribute no resources if the robber is here
        }

        for(Vertex v: vertices){
            if(v.settlement != null){
                v.settlement.collectResource(resource);
            }
        }
        return true;
    }

    public void generateAllSides(){
        edges = new Edge[6];
        vertices = new Vertex[6];
        for(int i = 0; i<6;i++){
            edges[i] = new Edge(i);
            vertices[i] = new Vertex(i);
        }
    }

    public void radius1Attachment(int dir, Hexagon[] hexes){
        if(dir>5 || dir<1){
            dir = 0;    //Keep orientation within the range, don't even accept outside
        }

        //adjacentHexes = hexes.clone();
        for(int i = 0; i<6; i++) {
            setAdjHexAndBack(i, hexes[i]);
        }

        //Connect (almost) all edges directly attached to hexagon
                // Have to be careful here, add 5 to the orientation to avoid negatives
        for(int cDir = dir; cDir != (dir+5)%6; cDir=(cDir+1)%6) {
            hexes[cDir].setEdge((cDir+3)%6, edges[cDir]);
        }

        for(int cDir = (dir+1)%6; cDir != (dir+5)%6; cDir=(cDir+1)%6){
            hexes[cDir].setVertex((cDir+4)%6, vertices[cDir]);
            hexes[(cDir+5)%6].setVertex((cDir+2)%6, vertices[cDir]);
        }
        //Now connect the needed components for the 2nd ring of hexagons, with dir + 3 providing the objects
        hexes[(dir+2)%6].setAdjHexAndBack((dir+4)%6, hexes[(dir + 3)%6]);
        hexes[(dir+2)%6].setEdge((dir+4)%6, hexes[(dir + 3)%6].getEdge((dir+1)%6));
        hexes[(dir+2)%6].setVertex((dir+4)%6, hexes[(dir + 3)%6].getVertex((dir+2)%6));

        hexes[(dir+4)%6].setAdjHexAndBack((dir+2)%6, hexes[(dir + 3)%6]);
        hexes[(dir+4)%6].setEdge((dir+2)%6, hexes[(dir + 3)%6].getEdge((dir+5)%6));
        hexes[(dir+4)%6].setVertex((dir+3)%6, hexes[(dir + 3)%6].getVertex((dir+5)%6));
            //Add one to the right-hex vertex index to align the correct vertex

    }   //Orientation is what side of the hexagon is the middle

    // Spin around clockwise until you find a hex without a token
    public int getNextTokenlessHex(int dir) throws IndexOutOfBoundsException {
        for(int cDir = dir; cDir != (dir+5)%6; cDir=(cDir+1)%6) {
            try{
                if(!adjacentHexes[cDir].hasTokenNum()){
                    return cDir;
                }
            }catch(Exception e){        //If there is not a hex in that direction, catch.
                continue;
            }

        }
        throw new IndexOutOfBoundsException("No hexes found");
    }

    public Catan.Resource getResourceType() {
        return resource;
    }

    public void setResourceType(Catan.Resource resource) {
        this.resource = resource;
        this.setImageFile(resource.getHexFilePath());
    }

    public Edge getEdge(int index){
        return edges[index%6];  //Modulo 6 to guarantee the index is within the 6 possible
    }

    public boolean setEdge(int index, Edge newEdge){
        edges[index%6] = newEdge;
        return true;
    }

    public Hexagon getAdjHex(int index){
        return adjacentHexes[index%6];  //Modulo 6 to guarantee the index is within the 6 possible
    }

    public boolean setAdjHexAndBack(int index, Hexagon newHex){
        adjacentHexes[index%6] = newHex;
        newHex.setAdjHex((index+3)%6, this);
        return true;
    }

    public boolean setAdjHex(int index, Hexagon newHex){
        adjacentHexes[index%6] = newHex;
        return true;
    }

    public Vertex getVertex(int index){
        return vertices[index%6];
    }

    public boolean setVertex(int index, Vertex newVertex){
        vertices[index%6] = newVertex;
        return true;
    }

    public int getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(int tokenNum) {
        this.tokenNum = tokenNum;
    }

    public boolean hasTokenNum(){
        return tokenNum>0;
    }

    public boolean hasRobber() {
        return hasRobber;
    }

    public void activateRobber() {
        this.hasRobber = true;
    }
    public void removeRobber() {
        this.hasRobber = false;
    }

    @Override
    public void setCenterX(int centerX) {
        super.setCenterX(centerX);
        int i = 0;
        for(Vertex v : vertices){
            v.setCenterX(centerX, i);
            i++;
        }
        i=0;
        for(Edge e: edges){
            e.setCenterX(centerX, i);
            i++;
        }
    }

    @Override
    public void setCenterY(int centerY) {
        super.setCenterY(centerY);
        int i = 0;
        for(Vertex v : vertices){
            v.setCenterY(centerY, i);
            i++;
        }
        i = 0;
        for(Edge e: edges){
            e.setCenterY(centerY, i);
            i++;
        }
    }
    public Vertex getVertexFromDegrees(double degrees) {
        for(int i = 0; i<6; i++){
            if(degrees<30*(2*i+1)){
                return getVertex(i);
            }
        }
        return getVertex(0);
    }

    public Edge getEdgeFromDegrees(double degrees) {
        for(int i = 0; i<6; i++){
            if(degrees<60*(i+1)){
                if(degrees - 60*i > 55){
                    System.out.println(degrees);
                    Edge e = getOutsideEdge(i+1);
                    return e != null ? e : getEdge(i);
                }else if(degrees - 60*i < 5) {
                    Edge e = getOutsideEdge(i);
                    return e != null ? e : getEdge(i);
                }
                return getEdge(i);
            }
        }
        return getEdge(0);
    }

    public Edge getOutsideEdge(int vDir){
        vDir = (vDir+6)%6;
        if(adjacentHexes[vDir]!=null){
            return adjacentHexes[vDir].getEdge((vDir+4)%6);
        }else if(adjacentHexes[(vDir+5)%6]!=null){
            return adjacentHexes[(vDir+5)%6].getEdge((vDir+1)%6);
        }
        return null;
    }

    public Vertex getOutsideVertex(int vDir){
        vDir = (vDir+6)%6;
        if(adjacentHexes[vDir]!=null){
            return adjacentHexes[vDir].getVertex((vDir+5)%6);
        }else if(adjacentHexes[(vDir+5)%6]!=null){
            return adjacentHexes[(vDir+5)%6].getVertex((vDir+1)%6);
        }
        return null;
    }

    public int getVertexDir(Vertex v) {
        for (int i = 0; i < 6; i++) {
            if (v == vertices[i]) {
                return i;
            }
        }
        System.out.println("Could not find vertex, returning 0");
        return 0;
    }

    public int getEdgeDir(Edge e) {
        for (int i = 0; i < 6; i++) {
            if (e == edges[i]) {
                return i;
            }
        }
        System.out.println("Could not find edge, returning 0");
        return 0;
    }


    public Hexagon getEdgeHexFromDegrees(double degrees) {
            for(int i = 0; i<6; i++){
                if(degrees<60*(i+1)){
                    if(degrees - 60*i > 55){
                        System.out.println(degrees);
                        Edge e = getOutsideEdge((i+1)%6);
                        return e != null ? adjacentHexes[(i+1)%6] : this;
                    }else if(degrees - 60*i < 5) {
                        Edge e = getOutsideEdge(i);
                        return e != null ? adjacentHexes[i] : this;
                    }
                    return this;
                }
            }
            return this;
        }
}
