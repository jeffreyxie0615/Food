// define enumerator of food
import java.io.File;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Swipe {
    public ArrayList<Food> foodList;
    private int ind = 0;
    private ObjectMapper mapper = new ObjectMapper();

    Swipe swiper = mapper.readValues(new File("/Users/tonywei/StudioProjects/Food/app/src/androidTest/java/Food"), Swipe.class);

    public Swipe() {
        while (this.ind < someSize) {
            foodList.add(new Food(someParameter));
            this.ind++;
        }
        this.ind = 0;
    }

    

    public void swipeLeft() {
        this.foodList.set(this.ind, this.foodList.get(this.ind).getRating() - 1);
    }

    public void swipeRight() {
        this.foodList.set(this.ind, this.foodList.get(this.ind).getRating() + 1);
    }
}
