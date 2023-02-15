package BoardData;

import Universal.Catan;

public class ResourceHex{

    Catan.Resource resource;
    int numToken;

    public ResourceHex(Catan.Resource resource, int numToken) {
        this.resource = resource;
        this.numToken = numToken;
    }
}
