import java.util.ArrayList;

enum Cuisine{
    MEXICAN, ITALIAN, ASIAN, AMERICAN, MEDITERRANEAN;
}

public class Profile {
    private String userName;
    private String password;
    private Cuisine cuisine;
    private Swipe swipe;

    public Profile(String password, String userName){
        this.userName = userName;
        this.password = password;
    }

    public void getSwipe(Swipe swipe){
        this.swipe = swipe;
    }



}
