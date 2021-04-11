package java;

import java.Food;
import java.Profile;
import java.util.Random;

import io.realm.RealmList;

public class Algo {
    private String cuisine = "";
    private float distance = 0;

    public Algo(Profile p) {
        RealmList<Food> list = p.foodList;
        float[] probabilities = new float[list.size()];

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] = (float) 1.0;
        }

        Random random = new Random();
        float rand = random.nextFloat();



        for (int i = 0; i < list.size(); i++) {
            probabilities[i] = (probabilities[i] * Integer.parseInt(list.get(i).rating)) / 50;

            if (probabilities[i] - rand< distance) {
                distance = probabilities[i] - rand;
            }
        }
    }

    public float returnDistance() {
        return distance;
    }
    public String getCuisine() {
        return this.cuisine;
    }


}
