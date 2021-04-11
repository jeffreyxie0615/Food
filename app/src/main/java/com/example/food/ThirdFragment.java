package com.example.food;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import com.squareup.picasso.*;
public class ThirdFragment extends Fragment {
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;
    private static int ind = 0;
    public static int i = 0;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String realmName = "Food";
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(3).deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).name(realmName).build();
        Realm backgroundThreadRealm = Realm.getInstance(config);
        RealmResults<Food> foods = backgroundThreadRealm.where(Food.class).findAll();
        ImageView iv = view.findViewById(R.id.imageView);
        String url = foods.get(i).pictures.get(ind).url;
        String label = foods.get(i).pictures.get(ind).label;
        Picasso.get().load(url).into(iv);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN: {
                        downX = event.getX();
                        downY = event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        upX = event.getX();
                        upY = event.getY();

                        float deltaX = downX - upX;
                        float deltaY = downY - upY;

                        // swipe horizontal?
                        if(Math.abs(deltaX) > Math.abs(deltaY))
                        {
                            if(Math.abs(deltaX) > MIN_DISTANCE){
                                // left or right
                                if(deltaX < 0) {
                                    System.out.println("here");
                                    backgroundThreadRealm.executeTransaction(transactionRealm -> {
                                        Food temp = new Food(foods.get(i).type, ((Integer)(Integer.valueOf(foods.get(i).rating) + 1)).toString(), foods.get(i).pictures);
                                        transactionRealm.insertOrUpdate(temp);
                                        System.out.println(foods);
                                    });
                                    ind++;
                                    if (ind == foods.get(i).pictures.size()) {
                                        ind = 0;
                                        i++;
                                        if (i==5) {
                                            i=0;
                                            ind=0;
                                            backgroundThreadRealm.executeTransaction(transactionRealm -> {
                                                Profile newPerson = new Profile(SecondFragment.currUser, SecondFragment.currPass, foods);
                                                transactionRealm.insertOrUpdate(foods);
                                            });
                                            NavHostFragment.findNavController(ThirdFragment.this)
                                                    .navigate(R.id.action_ThirdFragment_to_FourthFragment);
                                        }
                                    }

                                    String url = foods.get(i).pictures.get(ind).url;
                                    String label = foods.get(i).pictures.get(ind).label;
                                    Picasso.get().load(url).into(iv);

                                    return true;
                                }
                                if(deltaX > 0) {
                                    System.out.println("there");
                                    backgroundThreadRealm.executeTransaction(transactionRealm -> {
                                        Food temp = new Food(foods.get(i).type, ((Integer)(Integer.valueOf(foods.get(i).rating) - 1)).toString(), foods.get(i).pictures);
                                        transactionRealm.insertOrUpdate(temp);
                                        System.out.println(foods);
                                    });
                                    if (ind == foods.get(i).pictures.size()) {
                                        ind = 0;
                                        i++;
                                        if (i==5) {
                                            i=0;
                                            ind=0;
                                            NavHostFragment.findNavController(ThirdFragment.this)
                                                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);
                                        }
                                    }
                                    String url = foods.get(i).pictures.get(ind).url;
                                    String label = foods.get(i).pictures.get(ind).label;
                                    Picasso.get().load(url).into(iv);
                                    return true;
                                }
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });


    }

}