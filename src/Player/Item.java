package Player;

import java.util.ArrayList;

public class Item {
    Player owner;
    ArrayList<String> fileNames;

    public Item(Player owner) {
        this.owner = owner;
        fileNames = new ArrayList<>();
    }

    public String getFilePath(int index) {
        try{
            String fileName = fileNames.get(index);
            if(fileName == null){
                throw new ArrayIndexOutOfBoundsException();
            }
            return fileNames.get(index);
        }catch(Exception e){
            return "./CatanPNGs/Cards.png";
        }
    }

    public void addFilePath(String fileName) {
        fileNames.add(fileName);
    }

    public void updateFilePath(int index, String fileName) {
        fileNames.set(index, fileName);
    }
}
