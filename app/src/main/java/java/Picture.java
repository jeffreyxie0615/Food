package java;
import io.realm.RealmObject;
public class Picture extends RealmObject{
    public String url;
    public String label;

    public Picture(){};

    public Picture(String url, String label) {
        this.url = url;
        this.label = label;
    }
}
