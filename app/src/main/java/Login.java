import java.util.*;

public class Login {
    private String userName;
    private String password;

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void SetUsername (String word) {
        userName = word;
    }

    public void SetPassword(String word) {
        password = word;
    }

    public boolean CheckUser(String user, String pass) {
        if (user.equalsIgnoreCase(userName) && pass.equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }

    public void SignUp(String userName, String password) {
        Profile p = new Profile("","");

    }

}
