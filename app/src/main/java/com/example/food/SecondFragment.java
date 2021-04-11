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
                if (name.trim().length() < 1 || name == null || password.trim().length() < 1 || password == null) {
                    return;
                }
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
                boolean check = false;
                for (int i = 0; i < ppl.size(); i++) {
                    if (ppl.get(i).getUsername().trim().equals(user) && ppl.get(i).getPassword().trim().equals(password)) {
                        check = true;
                    }
                }
                if (!check) {
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
        String[] mexURL = {"https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHw%3D&w=1000&q=80", "https://docs.google.com/document/d/1DXIqjNts2CABKxxyYFt_WgwFs0Ot1aJKV1I49UHLqX4/edit", "https://lh3.googleusercontent.com/proxy/CHQ0rIprsq0P7Xs50WFFA3Ww8jVRQHS00E1uztQzSDnDKdaTqKDUi5Y9p31G0px5ErNftL9XlSwv1LPB7W-lH-YA1Da5BgpfeBgvP51N7zgsCGdcPA", "https://www.vhv.rs/dpng/d/422-4226860_nachos-png-png-download-nachos-transparent-background-png.png", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1zQuhWeV130MKVs30HTGDfdFBY9_mGf3HlQ&usqp=CAU"};
        String[] mexLabel = {"noodles", "xd", "xd", "xd", "xd"};
        Food mex = new Food("Mexican", "10", mexURL, mexLabel);

        String[] italURL = {"https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=700%2C636", "http://www.pngplay.com/wp-content/uploads/6/Dominos-Pizza-Transparent-File.png", "http://www.pngmart.com/files/5/Spaghetti-PNG-Image.png", "http://www.pngall.com/wp-content/uploads/5/Cheese-Lasagna-PNG.png", "https://banner2.cleanpng.com/20180601/sv/kisspng-risotto-vegetarian-cuisine-ravioli-chicken-nugget-risotto-illustration-5b111c54c11c08.260677341527848020791.jpg"};
        String[] italLabel = {"Gnocchi", "Pizza", "Spaghetti", "Lasagna", "Ravioli"};
        Food ita = new Food("Italian", "10", italURL, italLabel);

        String[] asaURL = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvxAJcSQRs2u2vkyS5GoKLm66Op0CqWt0rjg&usqp=CAU", "https://png.pngitem.com/pimgs/s/166-1663739_chinese-cuisine-chopsticks-chinese-food-png-transparent-png.png", "https://png.pngitem.com/pimgs/s/191-1914773_fresh-chinese-food-transparent-chinese-food-hd-png.png", "https://image.pngaaa.com/289/1359289-middle.png", "https://www.pngkey.com/png/detail/364-3647229_welcome-to-india-chaat-cafe-indian-curry-transparent.png"};
        String[] asaLabel = {"noodles", "xd", "xd", "xd", "xd"};
        Food asa = new Food("Asian", "10", asaURL, asaLabel);

        String[] ameURL = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1rYMfEECKYBDwAtRmDIASXVuLmOk-GShvbg&usqp=CAU", "http://www.pngplay.com/wp-content/uploads/4/Fried-Chicken-Transparent-Free-PNG.png", "https://banner2.cleanpng.com/20171219/f05/fries-png-5a38bb7fa65c08.2336916615136674556814.jpg", "https://icon2.cleanpng.com/20180206/uqw/kisspng-sausage-beefsteak-ribs-rib-eye-steak-cooked-meat-5a79790a50fb45.2919797515179102823317.jpg"};
        String[] ameLabel = {"Sweet Potato Fries", "Fried Chicken", "Normal Fries", "Steak"};
        Food ame = new Food("American", "10", ameURL, ameLabel);

        String[] medURL = {"https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHw%3D&w=1000&q=80", "https://img1.pnghut.com/9/12/7/vz5qaEZkZs/dish-mediterranean-cuisine-salad-turkish-food-sandwich.jpg", "https://img1.pnghut.com/20/14/20/jNiXBdpCxa/souvlaki-restaurant-cuisine-fast-food-greek-salad.jpg", "https://img.pngio.com/mediterranean-food-png-images-free-png-library-mediterranean-food-png-603_614.png"};
        String[] medLabel = {"noodles", "xd", "xd", "xd"};
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