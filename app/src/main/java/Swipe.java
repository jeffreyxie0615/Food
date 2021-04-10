// define enumerator of food
import java.util.*;

public class Swipe {
    public ArrayList<Food> foodList;
    private int ind = 0;

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
