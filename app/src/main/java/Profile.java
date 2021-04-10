import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

enum Cuisine{
    MEXICAN, ITALIAN, ASIAN, AMERICAN, MEDITERRANEAN;
}

public class Profile {
    private String userName;
    private String password;
    private Cuisine cuisine;
    private ObjectMapper mapper = new ObjectMapper();

    public Profile(String password, String userName){
        this.userName = userName;
        this.password = password;
    }


}
