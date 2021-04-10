import java.lang.reflect.Array;
import java.util.*;

public class Food {
    private String type;


    private ArrayList<Picture> info;
    private int rating;

    public Food(String type, String url, String label, int rating) {
        this.type = type;
        this.info = new ArrayList<Picture>();
        this.rating = rating;
    }
}
