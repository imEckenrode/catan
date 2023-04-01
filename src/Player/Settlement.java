package Player;
import Universal.Catan;

public class Settlement extends Item{
    //Could just implement isCity as a boolean in here and change the display and resources given based on that
    boolean isCity;


    public Settlement(Player owner) {
        super(owner);
        addFilePath("./CatanPNGs/Settlement.png");
        addFilePath("./CatanPNGs/City.png");     //TODO: Make this City
        this.isCity = false;
    }

    public Settlement(Player owner, boolean isCity) {
        super(owner);
        addFilePath("./CatanPNGs/Settlement.png");
        addFilePath("./CatanPNGs/City.png");
        this.isCity = isCity;
    }

    public void collectResource(Catan.Resource resource) {
        if(owner != null){
            owner.getHand().addResource(resource);
            if(isCity){
                owner.getHand().addResource(resource);
            }
        }
    }

    public String getFilePath() {
        return super.getFilePath(this.isACity()? 1 : 0);
    }

    public boolean isACity() {
        return isCity;
    }
}
