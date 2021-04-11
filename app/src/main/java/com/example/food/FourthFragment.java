package com.example.food;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.yelp.fusion.client.connection.*;
import com.yelp.fusion.client.models.SearchResponse;


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
    private final static String APIKey = "vHwW63JHeiitHViZ_g6Y8JQUw-P-enPX4zi3BGVZkBCa05IQs6UPcXX3QDyCvuQib2yLUULmud0UuHruGTcx2Rcj5fxD2eCPGemezWFNpdnAda9puXgoX_9SA0NzYHYx";

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
        YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(APIKey);
            Map<String, String> params = new HashMap<>();
            params.put("term", newPerson.cuisine);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }


            params.put("latitude", MainActivity.latitude);
            params.put("longitude", MainActivity.longitude);
            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            Response<SearchResponse> response = call.execute();
            System.out.println(response.body().getBusinesses().get(0).getName());
        } catch (IOException e) {
            System.out.println("here");
            System.out.println(e);
        }

    }
}