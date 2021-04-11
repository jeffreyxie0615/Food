package java;

import io.realm.RealmList;

public class Algo {
    private String cuisine = "";
    public Algo(Profile p) {
        RealmList<Food> list = p.foodList;
        int max = -1;
        for (int i = 0; i < list.size(); i++) {
            if (Integer.valueOf(list.get(i).rating) > max) {
                max = Integer.valueOf(list.get(i).rating);
                this.cuisine = list.get(i).type;
            }
        }
    }
    public String getCuisine() {
        return this.cuisine;
    }


}
