package java;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.realm.RealmObject;

public class Login extends RealmObject{
    private String userName;
    private String password;

    public Login(){}

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
        if (user.trim().equalsIgnoreCase(userName) && pass.trim().equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }


}
