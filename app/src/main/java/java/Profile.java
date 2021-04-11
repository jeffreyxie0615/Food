package java;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Profile extends RealmObject {
    @PrimaryKey public String username="";
    @Required public String password="";
    // realm doesn't support enum for some reason
    public String cuisine="";
    public RealmList<Food> foodList;

    public Profile(){}
    public Profile(String username, String password, RealmResults<Food> foods){
        this.username = username;
        this.password = password;
        this.foodList = new RealmList<>();
        this.foodList.addAll(foods.subList(0, foods.size()));
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
