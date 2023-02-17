package BoardData;

import Universal.Catan;

public class Port {
    Catan.Resource resource;    //DESERT = 3:1
    boolean owned;

    public Port(Catan.Resource resource) {
        this.resource = resource;
    }

    public Catan.Resource getResource() {
        return resource;
    }

    public void setResource(Catan.Resource resource) {
        this.resource = resource;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
