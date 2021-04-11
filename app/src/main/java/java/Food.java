package java;

import java.util.*;
import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

public class Food extends RealmObject{
    @PrimaryKey public String type;
    public RealmList<Picture> pictures;
    public String rating;

    public Food(){};

    public Food(String type, String rating, String[] url, String[] label) {
        this.type = type;
        this.rating = rating;
        this.pictures = new RealmList<>();
        for (int i = 0; i < label.length; i++) {
            pictures.add(new Picture(url[i], label[i]));
        }
    }public Food(String type, String rating, RealmList<Picture> pictures) {
        this.type = type;
        this.rating = rating;
        this.pictures = pictures;
    }

    public void incr() {
        this.rating = ((Integer)(Integer.valueOf(this.rating) + 1)).toString();
    }

    public void decr() {
        this.rating = ((Integer)(Integer.valueOf(this.rating) - 1)).toString();
    }

}
