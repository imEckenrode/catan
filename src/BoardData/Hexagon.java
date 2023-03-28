package BoardData;


import Universal.Catan;

//import static BoardData.Dir6.rot60;

public class Hexagon {
    Catan.Resource resource;
    public Edge[] edges;
    public Vertex[] vertices;

    public Hexagon[] adjacentHexes;
    int tokenNum;
    boolean hasRobber;
    int centerX;
    int centerY;
    public Hexagon() {
        adjacentHexes = new Hexagon[6];
        generateAllSides();
    }

    public Hexagon(Catan.Resource resource) {
        this.centerX = 0;
        this.centerY = 0;
        this.resource = resource;
        adjacentHexes = new Hexagon[6];
        generateAllSides();
    }

    public boolean distributeResources() {
        if(hasRobber() || resource == Catan.Resource.DESERT){
            return false;   //Distribute no resources if the robber is here
        }

        for(Vertex v: vertices){
            if(v.item != null){
                v.item.collectResource(resource);
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
        if(dir>6 || dir<1){
            dir = 0;    //Keep orientation within the range, don't even accept outside
        }

        adjacentHexes = hexes.clone();

        //Connect (almost) all edges directly attached to hexagon
                // Have to be careful here, add 5 to the orientation to avoid negatives
        for(int cDir = dir; cDir != (dir+5)%6; cDir=(cDir+1)%6) {
            hexes[cDir].setEdge((cDir+3)%6, edges[cDir]);
        }
        for(int cDir = (dir+1)%6; cDir != (dir+4)%6; cDir=(cDir+1)%6){
            hexes[cDir].setVertex((cDir+4)%6, vertices[cDir]);
            hexes[(cDir+5)%6].setVertex((cDir+2)%6, vertices[cDir]);
        }

        //Now connect the needed components for the 2nd ring of hexagons, with dir + 3 providing the objects
        hexes[(dir+2)%6].setAdjHex((dir+4)%6, hexes[(dir + 3)%6].getAdjHex((dir+2)%6));
        hexes[(dir+2)%6].setEdge((dir+4)%6, hexes[(dir + 3)%6].getEdge((dir+1)%6));
        hexes[(dir+2)%6].setVertex((dir+4)%6, hexes[(dir + 3)%6].getVertex((dir+2)%6));

                //TODO: Make sure setAdjHex works correctly
        hexes[(dir+4)%6].setAdjHex((dir+3)%6, hexes[(dir + 3)%6].getAdjHex((dir+2)%6));
        hexes[(dir+4)%6].setEdge((dir+2)%6, hexes[(dir + 3)%6].getEdge((dir+5)%6));
        hexes[(dir+4)%6].setVertex((dir+3)%6, hexes[(dir + 3)%6].getVertex((dir+2)%6));
            //Add one to the right-hex vertex index to align the correct vertex

    }   //Orientation is what side of the hexagon is the middle

    // Spin around clockwise until you find a hex without a token
    public Hexagon getNextTokenlessHex(int dir) throws IndexOutOfBoundsException {
        for(int cDir = dir; cDir != (dir+5)%6; cDir=(cDir+1)%6) {
            try{
                if(!adjacentHexes[cDir].hasTokenNum()){
                    return adjacentHexes[cDir];
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

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
    public void setCenterX(int centerX) {
        int changeInX = centerX - this.centerX;
        this.centerX = centerX;
        for(Vertex v : vertices){
            v.adjustCenterX(changeInX);
        }
        for(Edge e: edges){
            e.adjustCenterX(changeInX);
        }
    }

    public void setCenterY(int centerY) {
        int changeInY = centerY - this.centerY;
        this.centerY = centerY;
        for(Vertex v : vertices){
            v.adjustCenterY(changeInY);
        }
        for(Edge e: edges){
            e.adjustCenterY(changeInY);
        }
    }

}
