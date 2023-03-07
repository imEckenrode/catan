package BoardData;

public class Dir6{

    //Rotate 60 degrees per turn
    public static int rot60(int current, int turns){
        int shift = (current+turns)%6;
        //Return the positive remainder
        return shift<0 ? shift+6 : shift;
    }

}
