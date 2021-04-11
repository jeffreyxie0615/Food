package java;// define enumerator of food
import java.io.IOException;
import java.util.*;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.RealmResults;

import java.lang.*;
public class Swipe extends RealmObject{
    public RealmList<Food> foodList;
    private int ind = 0;

    public Swipe() {
        String realmName = "Food";
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(3).deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).name(realmName).build();
        Realm backgroundThreadRealm = Realm.getInstance(config);
        RealmResults<Food> foods = backgroundThreadRealm.where(Food.class).findAll();
        System.out.println(foods);

        this.ind = 0;
    }

    public void swipeLeft() {
        this.foodList.get(this.ind).rating = ((Integer)(Integer.valueOf(this.foodList.get(this.ind).rating) - 1)).toString();
    }

    public void swipeRight() {
        this.foodList.get(this.ind).rating = ((Integer)(Integer.valueOf(this.foodList.get(this.ind).rating) + 1)).toString();
    }
}
