package com.example.food;
import android.widget.EditText;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import io.realm.RealmResults;
import java.Login;
import java.Food;
import java.Profile;

public class SecondFragment extends Fragment {
    public static String currUser;
    public static String currPass;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String realmName = "Food";
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(3).deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).name(realmName).build();
        Realm backgroundThreadRealm = Realm.getInstance(config);
        EditText user = view.findViewById(R.id.editTextTextPersonName);
        EditText pass = view.findViewById(R.id.editTextTextPassword);
        createFoods(backgroundThreadRealm);
        view.findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String password = pass.getText().toString();
                Login newPerson = new Login(name, password);
                backgroundThreadRealm.executeTransaction (transactionRealm -> {
                    transactionRealm.insertOrUpdate(newPerson);
                });
                currUser = name;
                currPass = password;
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
        view.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String password = pass.getText().toString();
                RealmResults<Profile> ppl = backgroundThreadRealm.where(Profile.class).contains("username", name).findAll();
                if (ppl.size() == 0) {
                    return;
                }
                Profile currPerson = ppl.get(0);
                currUser = name;
                currPass = password;
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

    // make new food
    public void createFoods(Realm backgroundThreadRealm) {
        String[] mexURL = {"https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHw%3D&w=1000&q=80"};
        String[] mexLabel = {"noodles"};
        Food mex = new Food("Mexican", "10", mexURL, mexLabel);

        String[] italURL = {"https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=700%2C636"};
        String[] italLabel = {"noodles"};
        Food ita = new Food("Italian", "10", italURL, italLabel);

        String[] asaURL = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvxAJcSQRs2u2vkyS5GoKLm66Op0CqWt0rjg&usqp=CAU"};
        String[] asaLabel = {"noodles"};
        Food asa = new Food("Asian", "10", asaURL, asaLabel);

        String[] ameURL = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1rYMfEECKYBDwAtRmDIASXVuLmOk-GShvbg&usqp=CAU"};
        String[] ameLabel = {"noodles"};
        Food ame = new Food("American", "10", ameURL, ameLabel);

        String[] medURL = {"https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHw%3D&w=1000&q=80"};
        String[] medLabel = {"noodles"};
        Food med = new Food("Mediterranean", "10", medURL, medLabel);
        backgroundThreadRealm.executeTransaction (transactionRealm -> {
            transactionRealm.insertOrUpdate(mex);
            transactionRealm.insertOrUpdate(asa);
            transactionRealm.insertOrUpdate(ame);
            transactionRealm.insertOrUpdate(med);
            transactionRealm.insertOrUpdate(ita);
        });
    }
}