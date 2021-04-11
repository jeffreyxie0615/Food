package java;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Login {
    private String userName;
    private String password;
    private ObjectMapper mapper =  new ObjectMapper();

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

    public void SignUp(String userName, String password, String path) {
        Profile p = new Profile(userName, password);
        try {
            mapper.writeValue(new File(path), p);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
