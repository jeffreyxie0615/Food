package java;

import android.util.Pair;

public class CreditCard {
    public int number;
    public int date;
    public String name;
    public int security;

    public CreditCard(String num, String date, String name, String sec) {
        this.number = Integer.parseInt(num);
        this.date = Integer.parseInt(date);
        this.name = name;
        this.security = Integer.parseInt(sec);
    }
}
