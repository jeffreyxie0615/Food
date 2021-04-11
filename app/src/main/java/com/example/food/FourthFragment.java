package com.example.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.net.HttpURLConnection;
import java.net.URL;
import java.Algo;
import java.Profile;

import io.realm.*;
public class FourthFragment extends Fragment {

    private final static String clientID = "G9ds1N_jO7s-9TTA2Q-seQ";
    private final static String APIKey = "vHwW63JHeiitHViZ_g6Y8JQUw-P-enPX4zi3BGVZkBCa05IQs6UPcXX3QD" +
            "yCvuQib2yLUULmud0UuHruGTcx2Rcj5fxD2eCPGemezWFNpdnAda9puXgoX_9SA0NzYHYx";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Realm.init(getContext());
        String realmName = "Food";
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(3).deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).name(realmName).build();
        Realm backgroundThreadRealm = Realm.getInstance(config);
        backgroundThreadRealm.executeTransaction(transactionRealm -> {
            Profile newPerson = backgroundThreadRealm.where(Profile.class).contains("username", SecondFragment.currUser).findAll().get(0);
            Algo work = new Algo(newPerson);
            newPerson.cuisine = work.getCuisine();
            transactionRealm.insertOrUpdate(newPerson);
        });
        try {
            URL url = new URL("https://api.yelp.com/oauth2/token?grant_type=client_credentials&client_secret=vHwW63JHeiitHViZ_g6Y8JQUw-P-enPX4zi3BGVZkBCa05IQs6UPcXX3QD\" +\n" +
                    "            \"yCvuQib2yLUULmud0UuHruGTcx2Rcj5fxD2eCPGemezWFNpdnAda9puXgoX_9SA0N" +
                    "zYHYx&client_id=G9ds1N_jO7s-9TTA2Q-seQ");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Object yelp = con.getContent();
            System.out.println("yelp");
            System.out.println(yelp);
            System.out.println("yelp");
        }catch (Exception e){}

    }
}