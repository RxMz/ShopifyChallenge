package minocha.rishabh.shopifychallenge.Converters;

/**
 * Created by rkminoch on 08/05/18.
 */

public class StampToYear {
    public static String getYear(String stamp){
        return ""+stamp.charAt(0)+stamp.charAt(1)+stamp.charAt(2)+stamp.charAt(3);
    }
}
