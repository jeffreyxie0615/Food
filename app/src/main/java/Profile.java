enum Cuisine{
    MEXICAN, ITALIAN, ASIAN, AMERICAN, MEDITERRANEAN;
}

public class Profile {
    private String userName;
    private String password;
    private Cuisine cuisine;

    public Profile(String password, String userName){
        this.userName = userName;
        this.password = password;
    }

}
