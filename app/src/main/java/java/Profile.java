package java;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Profile extends RealmObject {
    @PrimaryKey String userName="";
    @Required String password="";
    // realm doesn't support enum for some reason
    private String cuisine="";
    private RealmList<Food> foodList=null;

    public Profile(){}
    public Profile(String password, String userName){
        this.userName = userName;
        this.password = password;
        Swipe temp = new Swipe();
        this.foodList = temp.foodList;
    }



}
