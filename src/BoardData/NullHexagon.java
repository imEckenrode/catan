package BoardData;


import Universal.Catan;

//import static BoardData.Dir6.rot60;

public class NullHexagon extends Hexagon{
    Catan.Resource resource;
    public Edge[] edges;
    public Vertex[] vertices;

    public NullHexagon[] adjacentHexes;

    int tokenNum;
    boolean hasRobber;

    public NullHexagon() {
        this.resource = resource;
        this.edges = edges;
        this.vertices = vertices;
        this.adjacentHexes = adjacentHexes;
        this.tokenNum = 0;
        this.hasRobber = false;
    }
}
