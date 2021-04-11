package App;

import java.util.Random;

import io.realm.RealmList;

public class Algo {
    private String cuisine = "";

    public Algo(Profile p) {
        RealmList<Food> list = p.foodList;
        float[] probabilities = new float[list.size()];

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] = (float) 1.0;
        }

        Random random = new Random();
        float rand = random.nextFloat();

        float distance = 0;

        for (int i = 0; i < list.size(); i++) {
            probabilities[i] = (probabilities[i] * Integer.parseInt(list.get(i).rating)) / 50;

            if (probabilities[i] - rand< distance) {
                distance = probabilities[i] - rand;
            }
        }




    }

    public String getCuisine() {
        return this.cuisine;
    }


}
