// define enumerator of food
import java.io.IOException;
import java.util.*;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Swipe {
    public ArrayList<Food> foodList;
    private int ind = 0;

    public Swipe() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("../json/foods.json");
        while (this.ind < 6) {
            try {
                foodList.add(mapper.readValue(file, Food.class));
            } catch(IOException e) {
                System.out.println(e);
            }
            this.ind++;
        }
        this.ind = 0;
    }

    public void swipeLeft() {
        this.foodList.get(this.ind).rating = this.foodList.get(this.ind).rating - 1;
    }

    public void swipeRight() {
        this.foodList.get(this.ind).rating = this.foodList.get(this.ind).rating + 1;
    }
}
