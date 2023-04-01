package Universal;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dice {
    //This could even be a method within Main/Catan, but it also needs a GUI representation so...

    Random rng;

    public Dice() {
        rng = new Random();
    }

    public int rollDice(){
        return rng.nextInt(6)+rng.nextInt(6)+2; //nextInt returns a number between 0 and bound-1, so add 1 to each die roll
    }
    public ArrayList<Integer> rollDiceSeparately(){
        return new ArrayList<>(Arrays.asList(rng.nextInt(6)+1, rng.nextInt(6)+1));
    }

    public int randomInt(int range){
        return rng.nextInt(range);
    }

}
