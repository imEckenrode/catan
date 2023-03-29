package Player;

import java.util.ArrayList;

public class Item {
    Player owner;
    String fileName;
    ArrayList<String> fileNames;

    public Item(Player owner) {
        this.owner = owner;
        fileNames = new ArrayList<>();
    }

    public String getFileName(int index) {
        try{
            return fileNames.get(index);
        }catch(Exception e){
            return "./CatanPNGs/Cards.png";
        }
    }

    public void setFileName(int index, String fileName) {
        fileNames.set(index, fileName);
        this.fileName = fileName;
    }
}
