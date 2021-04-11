package com.example.food;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.CreditCard;
import java.io.IOException;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.Algo;
import java.Profile;

import io.realm.*;
import retrofit2.Call;
import retrofit2.Response;

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
        Profile newPerson = backgroundThreadRealm.where(Profile.class).contains("username", SecondFragment.currUser).findAll().get(0);
        YelpAPIFactory apiFactory = new YelpAPIFactory(clientID, APIKey, "", "");
        YelpAPI yelpAPI = apiFactory.createAPI();
        Map<String, String> params = new HashMap<>();
        params.put("term", newPerson.cuisine);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String temp = params.get("term");
            Call<SearchResponse> call = yelpAPI.search("Austin", params);
            SearchResponse searchResponse = call.execute().body();
            int totalNumberOfResult = searchResponse.total();  // 3
            ArrayList<Business> businesses = searchResponse.businesses();
            String businessName = businesses.get(0).name();
            Double rating = businesses.get(0).rating();
            System.out.println(businessName);
        } catch (IOException e) {
            System.out.println(e);
        }


        view.findViewById(R.id.order_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = view.findViewById(R.id.editTextNumberPassword3);
                EditText date = view.findViewById(R.id.editTextDate);
                EditText number = view.findViewById(R.id.editTextNumberPassword);
                EditText security = view.findViewById(R.id.editTextNumberPassword2);

                String tempName = name.toString();
                String tempNumber = number.toString();
                String tempDate = date.toString();
                String tempSecurity = security.toString();

                CreditCard card = new CreditCard(tempNumber, tempDate, tempName, tempSecurity);

                backgroundThreadRealm.executeTransaction (transactionRealm -> {
                    transactionRealm.insertOrUpdate(card);
                });

                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.);
            }
        });

    }
}