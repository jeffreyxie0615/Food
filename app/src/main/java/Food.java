import java.util.*;
public class Food {
    private String type;
    private ArrayList<Picture> pictures;
    public int rating;

    public Food(String type, ArrayList<Picture> pictures, int rating) {
        this.type = type;
        this.pictures = pictures;
        this.rating = rating;
    }
}
