// define enumerator of food
import java.io.IOException;
import java.util.*;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        int temp = this.foodList.get(this.ind).rating;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objectMapper.readTree(new File("../json/foods.json"));
        }catch(IOException e) {
            System.out.println(e);
        }
        JsonNode steps = root.get("type");
        for (final JsonNode item : root) {
            if (item.findPath("type").asText().equals(this.foodList.get(this.ind).type)) {
                ((ObjectNode) item).put("rating", temp);
            }
        }
    }

    public void swipeRight() {
        this.foodList.get(this.ind).rating = this.foodList.get(this.ind).rating + 1;
        int temp = this.foodList.get(this.ind).rating;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objectMapper.readTree(new File("../json/foods.json"));
        }catch(IOException e) {
            System.out.println(e);
        }
        JsonNode steps = root.get("type");
        for (final JsonNode item : root) {
            if (item.findPath("type").asText().equals(this.foodList.get(this.ind).type)) {
                ((ObjectNode) item).put("rating", temp);
            }
        }
    }
}
