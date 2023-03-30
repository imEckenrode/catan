package Player;

public class Road extends Item{

    public Road(Player owner) {
        super(owner);
        addFilePath("./CatanPNGs/Road.png");
        //addFileName(1,"./CatanPNGs/brick.png");   //Do we want 3 possible Roads and orient based on the orientation?
    }
}
