package com.example.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.Algo;
import java.Profile;

import io.realm.*;
public class FourthFragment extends Fragment {

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
    }
}